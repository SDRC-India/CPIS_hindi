package org.sdrc.cpis.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sdrc.cpis.domains.AreaDetails;
import org.sdrc.cpis.domains.CciUserMapping;
import org.sdrc.cpis.domains.Designation;
import org.sdrc.cpis.domains.DesignationFeaturePermissionMapping;
import org.sdrc.cpis.domains.EmailActivation;
import org.sdrc.cpis.domains.FeaturePermissionMapping;
import org.sdrc.cpis.domains.Features;
import org.sdrc.cpis.domains.Permissions;
import org.sdrc.cpis.domains.UserDetails;
import org.sdrc.cpis.models.DesignationFeaturePermissionModel;
import org.sdrc.cpis.models.DesignationModel;
import org.sdrc.cpis.models.FeatureModel;
import org.sdrc.cpis.models.FeaturePermissionMappingModel;
import org.sdrc.cpis.models.Mail;
import org.sdrc.cpis.models.PermissionModel;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.CciUserRepository;
import org.sdrc.cpis.repository.EmailActivationRepository;
import org.sdrc.cpis.repository.TxnUserLoginMetaRepository;
import org.sdrc.cpis.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private MailService mailService;
	
	private UserDetails userDetails;
	
	@Autowired
	private EmailActivationRepository emailActivationRepository;
	
	@Autowired
	private MessageDigestPasswordEncoder messageDigest;
	
	@Autowired
	private CciUserRepository cciUserRepository;
	
	@Autowired
	private TxnUserLoginMetaRepository txnUserLoginMetaRepository;
	
	@Override
	public UserDetailModel findActiveUserById(String userName)
			throws DataAccessException {
		userDetails = userDetailRepository.findActiveUserById(userName);
		
		UserDetailModel userDetailModel = new UserDetailModel();
		
		userDetailModel.setPassword(userDetails.getPassword());
		userDetailModel.setUserId(userDetails.getUserId());
		userDetailModel.setUserName(userDetails.getUserName());
		userDetailModel.setName(userDetails.getCwcName());
		userDetailModel.setDesignationId(userDetails.getDesignation().getDesignationId());;
		userDetailModel.setAreaId(userDetails.getArea().getAreaId());
		userDetailModel.setEmail(userDetails.getEmail());
		userDetailModel.setCciId(cciUserRepository.findByUserId(userDetails.getUserId())==null?null:cciUserRepository.findByUserId(userDetails.getUserId()).getCciId());
		userDetailModel.setAreaLevelId(userDetails.getArea().getAreaLevel().getLevelId());
		userDetailModel.setAreaName(userDetails.getArea().getAreaName());
		userDetailModel.setDesignationModel(getDesignationModel());
		
		return userDetailModel;
	}
	
	
	private DesignationModel getDesignationModel(){
		DesignationModel designationModel = new DesignationModel();
		designationModel.setDesignationId(userDetails.getDesignation().getDesignationId());
		designationModel.setDesignationName(userDetails.getDesignation().getDesignationName());
		designationModel.setDesignationAreaLevel(userDetails.getDesignation().getAreaLevel().getLevelId());
		designationModel.setDesignationFeaturePermissionMappings(getDesignationFeaturePermissionMappings());;
		return designationModel;
	}
	
	private List<DesignationFeaturePermissionModel> getDesignationFeaturePermissionMappings(){
		List<DesignationFeaturePermissionModel> designationFeaturePermissionModels=new ArrayList<>();
		List<DesignationFeaturePermissionMapping> designationFeaturePermissionMappings=userDetails.getDesignation().getDesignationFeaturePermissionMappings();
		for (int i=0;i<designationFeaturePermissionMappings.size();i++) {
			DesignationFeaturePermissionModel designationFeaturePermissionModel=new DesignationFeaturePermissionModel();
			designationFeaturePermissionModel.setDesignationFeaturePermissionId(designationFeaturePermissionMappings.get(i).getDesignationFeaturePermissionId());
//			designationFeaturePermissionModel.setDesignation(getDesignationModel());
			designationFeaturePermissionModel.setFeaturePermissionMapping(getFeaturePermissionModel(designationFeaturePermissionMappings.get(i).getFeaturePermissionMapping()));
		
			designationFeaturePermissionModels.add(designationFeaturePermissionModel);
		}
		return designationFeaturePermissionModels;
	}
	
	private FeaturePermissionMappingModel getFeaturePermissionModel(FeaturePermissionMapping fpm){
		FeaturePermissionMappingModel featurePermissionMappingModel = new FeaturePermissionMappingModel();
		featurePermissionMappingModel.setFeaturePermissionId(fpm.getFeaturePermissionId());
		featurePermissionMappingModel.setFeature(getFeatureModel(fpm.getFeatures()));
		featurePermissionMappingModel.setPermission(getPermissionModel(fpm.getPermissions()));
		return featurePermissionMappingModel;
	}
	
	private FeatureModel getFeatureModel(Features feature){
		FeatureModel featureModel = new FeatureModel();
		featureModel.setFeatureId(feature.getFeatureId());
		featureModel.setFeatureName(feature.getFeatureName());
		return featureModel;
	}
	
	private PermissionModel getPermissionModel(Permissions permission){
		PermissionModel permissionModel = new PermissionModel();
		permissionModel.setPermissionId(permission.getPermissionsId());
		permissionModel.setPermissionName(permission.getPermissionsName());
		return permissionModel;
	}

	@Override
	public long saveLoginMeta(UserDetailModel userDetailModel)
			throws DataAccessException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Transactional
	@Override
	public void saveNewUser(UserDetailModel userDetailModel,String url, String password) {
		// TODO Auto-generated method stub
		UserDetails userDetails = new UserDetails();
		AreaDetails areaDetails = new AreaDetails();
		Designation designation = new Designation();
		UserDetails user=null;
		CciUserMapping cciUserMapping = new CciUserMapping();
		Mail mail = new Mail();
		List<String> mailList=new ArrayList<>();
		mailList.add(userDetailModel.getEmail());
		mail.setToEmailIds(mailList);
		mail.setToUserName(userDetailModel.getUserName());
		
		
		if(userDetailRepository.findByUniqueEmail(userDetailModel.getEmail())!=null) {
			userDetails=userDetailRepository.findByEmail(userDetailModel.getEmail());
			userDetails.setLive(true);
			user=userDetailRepository.save(userDetails);
		}
		else {
			designation.setDesignationId(userDetailModel.getDesignationId());
			areaDetails.setAreaId(userDetailModel.getAreaId());
			userDetails.setUserName(userDetailModel.getUserName());
			userDetails.setCwcName(userDetailModel.getName());
			userDetails.setEmail(userDetailModel.getEmail());
			userDetails.setArea(areaDetails);
			userDetails.setDesignation(designation);
			userDetails.setPassword(userDetailModel.getPassword());
			userDetails.setCreatedDate(new Timestamp(new Date().getTime()));
			userDetails.setLive(true);
			
			try{
				user=userDetailRepository.save(userDetails);
				}catch(Exception exception){
					System.out.println("Error while saving!!!");
				}
		}
		
		if(userDetailModel.getDesignationId()==10){
		cciUserMapping.setCciId(userDetailModel.getCciId());
		cciUserMapping.setUserId(user.getUserId());
		cciUserRepository.save(cciUserMapping);
		}
		
		try{
			mailService.sendConfirmationMail(userDetailModel,mail,url,password);
		}catch(Exception exception){
			System.out.println("Error while sending mail!!!");
			
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
	
	@Override
	public String updateActivatedAccount(Integer mId){
		emailActivationRepository.updateActivationInfo(mId);
		return "true";
	}
	
	@Override
	public String updateForgotPassword(String email, String password){
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
	public String changeUserPassword(String email, String password) {
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
	public String changeUserPasswordByAdmin(String email, String password, String newEmail) {
		UserDetails userDetails=userDetailRepository.findByEmail(email);
		UserDetails duplicateUser = userDetailRepository.findByEmail(newEmail);
		if(userDetails!=null){
			if(duplicateUser==null) {
			String encodePassword = messageDigest.encodePassword(newEmail.split("@")[0], password);
			
			userDetailRepository.resetUserPasswordByAdmin(email, encodePassword, newEmail);
			return "true";	
			}else {
				return "email_exists";
			}
		}
		else
			return "no_user";
	}


	@Transactional
	@Override
	public String deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		txnUserLoginMetaRepository.deleteLoginMetaData(userId);
		cciUserRepository.deleteByUserId(userId);
		userDetailRepository.deleteUser(userId);
		return "success";
	}
}
