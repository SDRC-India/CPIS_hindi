package org.sdrc.cpis.services;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CounterCount;
import org.sdrc.cpis.domains.Designation;
import org.sdrc.cpis.domains.EmailActivation;
import org.sdrc.cpis.domains.TXN_User_LogIn_Meta;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.AreaDetailsModel;
import org.sdrc.cpis.models.DesignationModel;
import org.sdrc.cpis.models.Mail;
import org.sdrc.cpis.models.TXN_User_LogIn_Meta_Model;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.AreaRepository;
import org.sdrc.cpis.repository.CounterCountRepository;
import org.sdrc.cpis.repository.DesignationRepository;
import org.sdrc.cpis.repository.EmailActivationRepository;
import org.sdrc.cpis.repository.TxnUserLoginMetaRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class LoginServiceImplementation implements LoginService {

	@Autowired 
	private AreaRepository areaRepository;
	
	@Autowired
	private DesignationRepository designationRepository;
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EmailActivationRepository emailActivationRepository;
	
	@Autowired
	private MessageDigestPasswordEncoder messageDigest;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private TxnUserLoginMetaRepository txnUserLoginMetaRepository;
	
	@Autowired
	private CounterCountRepository counterCountRepository;
	
	
	@Override
	public Map<String, Object> fetchLoginData() {
		Map<String, Object> mstData = new HashMap<String, Object>();
		
		List<AreaDetails> areaList = areaRepository.fetchAllArea();
		List<AreaDetailsModel> areaModels=new ArrayList<>();
		for (AreaDetails areaDetails : areaList) {
			AreaDetailsModel areaModel=new AreaDetailsModel();
			areaModel.setAreaId(areaDetails.getAreaId());
			areaModel.setAreaName(areaDetails.getAreaName());
			areaModel.setParentAreaId(null == areaDetails.getParentArea() ? null : areaDetails.getParentArea().getAreaId());
			areaModel.setAreaLevel(areaDetails.getAreaLevel().getLevelId());
			areaModel.setAreaCode(null == areaDetails.getAreaCode() ? null : areaDetails.getAreaCode());
			
			areaModels.add(areaModel);
		}
		
		List<Designation> designationList = designationRepository.fetchAllDesignations();
		List<DesignationModel> designationModels=new ArrayList<>();
		for (Designation designation : designationList) {
			DesignationModel designationModel=new DesignationModel();
			designationModel.setDesignationId(designation.getDesignationId());
			designationModel.setDesignationName(designation.getDesignationName());
			designationModel.setDesignationAreaLevel(designation.getAreaLevel().getLevelId());
			
			designationModels.add(designationModel);
		}
		
		List<UserDetails> userDetailsList = userDetailRepository.fetchAllUsers();
		List<UserDetailModel> userDetailModels = new ArrayList<>();
		for(UserDetails userDetails : userDetailsList){
			UserDetailModel userDetailModel = new UserDetailModel();
			userDetailModel.setEmail(userDetails.getEmail());
			userDetailModel.setPassword(userDetails.getPassword());
			userDetailModel.setUserId(userDetails.getUserId());
			userDetailModel.setUserName(userDetails.getUserName());
			userDetailModel.setAreaId(userDetails.getArea().getAreaId());
			userDetailModel.setDesignationId(userDetails.getDesignation().getDesignationId());
			userDetailModel.setName(userDetails.getCwcName());
			
			userDetailModels.add(userDetailModel);
		}
		
		mstData.put("areaDetails", areaModels);
		mstData.put("designation", designationModels);
		mstData.put("userList", userDetailModels);
		
		return mstData;
	}
	
	@Override
	public String forgotWebPassword(String email, String url) throws UnsupportedEncodingException {
		
		UserDetails userDetails=userDetailRepository.findByEmail(email);
		
		if(userDetails!=null){

			List<String> emailId = new ArrayList<String>();
			emailId.add(email);
			
			Mail mail = new Mail();
			mail.setToEmailIds(emailId);
			mail.setUserId(userDetails.getUserId());
			mail.setToUserName(userDetails.getCwcName());
			
			Integer mId=mailService.saveMailSendInfo(mail);
			
			String eId = mId+","+ email;
			String encodedEId = Base64.getEncoder().encodeToString(eId.getBytes("UTF-8"));
			
			mail.setMsg(encodedEId);
			mail.setServerUrl(url);
	
			EmailActivation emailActivation=emailActivationRepository.findByEmailIdAndUserIdAndIsLiveTrue(email, userDetails.getUserId());
			
			if(emailActivation!=null){
				mailService.userResetPasswordMail(mail);			
				return "true";
			}else
					return "sentMail";
		} else {
			return "false";
		}
	}
	
	@Override
	@Transactional
	public String getActivatedEmailId(Integer mId, long currentTime){
		EmailActivation emailActivation=emailActivationRepository.getIsActiveEmailId(mId);
		long ns=60000;
		long sentTime=emailActivation.getSentDate().getTime();
		long diff=(currentTime/ns)-(sentTime/ns);
		if(emailActivation!=null && !emailActivation.getIsActive())
		{
			if(diff<=30)
				return "true";
			else
				return "timeOver";
		}
		else
			return "false";
	}
	/* 
	 * Web activation information updated
	*/
	public String updateActivatedAccount(Integer mId){
		emailActivationRepository.updateActivationInfo(mId);
		return "true";
	}
	
	/*
	 * Web password updation
	*/
	@Override
	public String updateWebForgotPassword(String email, String password){
		UserDetails userDetails=userDetailRepository.findByEmail(email);
		if(userDetails!=null){
			String encodePassword = messageDigest.encodePassword(email.split("@")[0], password);
			
			userDetailRepository.resetUserPasswordByEmail(email, encodePassword);
			return "true";	
		}
		else
			return "false";
	}
	
	@Override
	@Transactional
	public long saveTxnLoginMeta(TXN_User_LogIn_Meta_Model txn_User_LogIn_Meta_Model) throws DataAccessException {
		
		TXN_User_LogIn_Meta txn_User_LogIn_Meta=new TXN_User_LogIn_Meta();
		txn_User_LogIn_Meta.setDatetime_Logged_IN(new Timestamp(new Date().getTime()));
		txn_User_LogIn_Meta.setUserAgent(txn_User_LogIn_Meta_Model.getUserAgent());
		txn_User_LogIn_Meta.setUserIp(txn_User_LogIn_Meta_Model.getUserIp());
		txn_User_LogIn_Meta.setLive(true);
		txn_User_LogIn_Meta.setUserDetails(txn_User_LogIn_Meta_Model.getUserDetails());
		
		return txnUserLoginMetaRepository.save(txn_User_LogIn_Meta).getTxnId();
	}
	
	@Override
	@Transactional
	public void updateLoginMeta(long txnId, Timestamp datetimeLoggedOut) throws DataAccessException {
		if(txnId>0)
		{
		txnUserLoginMetaRepository.updateStatus(datetimeLoggedOut, txnId);
		}
		// whenever server will startup this method will get executed automatically
		else if(txnId==-1)
		{
			txnUserLoginMetaRepository.updateStatusForAll(datetimeLoggedOut);
		}
	}
	
	@Override
	public TXN_User_LogIn_Meta_Model getExistingLogInUser(Integer userId){
		TXN_User_LogIn_Meta user_LogIn_Meta = txnUserLoginMetaRepository.getExistingLogInUser(userId);
		TXN_User_LogIn_Meta_Model logIn_Meta_Model = new TXN_User_LogIn_Meta_Model();
		logIn_Meta_Model.setDatetime_Logged_IN(null==user_LogIn_Meta?null:user_LogIn_Meta.getDatetime_Logged_IN());
		logIn_Meta_Model.setLive(null==user_LogIn_Meta?null:user_LogIn_Meta.isLive());
		logIn_Meta_Model.setTxnId(null==user_LogIn_Meta?null:user_LogIn_Meta.getTxnId());
		logIn_Meta_Model.setUserAgent(null==user_LogIn_Meta?null:user_LogIn_Meta.getUserAgent());
		logIn_Meta_Model.setUserDetails(null==user_LogIn_Meta?null:user_LogIn_Meta.getUserDetails());
		
		return logIn_Meta_Model;
	}

	@Override
	public TXN_User_LogIn_Meta_Model getExistingUserByTxnId(Long txnId) {
		// TODO Auto-generated method stub
		TXN_User_LogIn_Meta user_LogIn_Meta = txnUserLoginMetaRepository.getExistingUserByTxnId(txnId);
		TXN_User_LogIn_Meta_Model logIn_Meta_Model = new TXN_User_LogIn_Meta_Model();
		logIn_Meta_Model.setDatetime_Logged_IN(null==user_LogIn_Meta?null:user_LogIn_Meta.getDatetime_Logged_IN());
		logIn_Meta_Model.setLive(null==user_LogIn_Meta?false:user_LogIn_Meta.isLive());
		logIn_Meta_Model.setTxnId(null==user_LogIn_Meta?null:user_LogIn_Meta.getTxnId());
		logIn_Meta_Model.setUserAgent(null==user_LogIn_Meta?null:user_LogIn_Meta.getUserAgent());
		logIn_Meta_Model.setUserDetails(null==user_LogIn_Meta?null:user_LogIn_Meta.getUserDetails());
		return logIn_Meta_Model;
	}
	
	
	@Override
	public CounterCount getCounter()  {
		CounterCount counterCount = counterCountRepository.findTotalCount();
		if(counterCount==null){
			CounterCount counterCountAdd = new CounterCount();
			counterCountAdd.setNoOfCounter(1);
			counterCountRepository.save(counterCountAdd);
		}
		else{
			counterCount.setNoOfCounter(counterCount.getNoOfCounter()+1);
			counterCountRepository.save(counterCount);
		}
		return counterCount;
	}

	@Override
	public List<AreaDetailsModel> getAllAreaForLoginUser() {
		UserDetailModel userDetailModel = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		int userAreaId = userDetailModel.getAreaId();
		List<AreaDetails> areaList=new ArrayList<AreaDetails>();
		if (userDetailModel.getAreaLevelId() == 2 || userDetailModel.getAreaLevelId() == 1) {
			areaList = areaRepository.findAllByParentAreaParentAreaAreaIdOrParentAreaAreaIdOrAreaId(userAreaId,userAreaId,userAreaId);
			areaList = areaList.stream().filter(p -> {
				return p.getAreaLevel().getLevelId()!=2;
			}).collect(Collectors.toList());
		} else if (userDetailModel.getAreaLevelId() == 3) {
			areaList = areaRepository.findAllByParentAreaAreaIdOrAreaId(userAreaId,userAreaId);
			areaList = areaList.stream().filter(p -> {
				return p.getAreaLevel().getLevelId()!=3;
			}).collect(Collectors.toList());
		} else {
			areaList = areaRepository.findAllByAreaId(userAreaId);
		}
		
//		List<AreaDetails> areaList = areaRepository.fetchAreaByLevel(userDetailModel.getAreaLevelId());
		List<AreaDetailsModel> areaModels=new ArrayList<>();
		for (AreaDetails areaDetails : areaList) {
			AreaDetailsModel areaModel=new AreaDetailsModel();
			areaModel.setAreaId(areaDetails.getAreaId());
			areaModel.setAreaName(areaDetails.getAreaName());
			areaModel.setParentAreaId(null == areaDetails.getParentArea() ? null : areaDetails.getParentArea().getAreaId());
			areaModel.setAreaLevel(areaDetails.getAreaLevel().getLevelId());
			areaModel.setAreaCode(null == areaDetails.getAreaCode() ? null : areaDetails.getAreaCode());
			
			areaModels.add(areaModel);
		}
		return areaModels;
	}
}
