package org.sdrc.cpis.controller;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sdrc.cpis.core.Authorize;
import org.sdrc.cpis.models.FollowUpFormModel;
import org.sdrc.cpis.models.Mail;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.CCIInfoService;
import org.sdrc.cpis.services.FollowUpFormService;
import org.sdrc.cpis.services.JobService;
import org.sdrc.cpis.services.MailService;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowUpController {

	@Autowired
	private FollowUpFormService followUpFormService;
	
	@Autowired
	private StateManager stateManager;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private CCIInfoService cciInfoService;
	
//	@Authorize(feature="followUpForm",permission="edit")
	@ResponseBody
	@RequestMapping(value={"/saveFollowUpForm"}, method = {RequestMethod.POST})
	public String saveFollowUpForm(@RequestBody FollowUpFormModel followUpFormModel) throws Exception{
		System.out.println(followUpFormModel.toString());
		followUpFormService.save(followUpFormModel);
		return "success";
	}
	
	@Authorize(feature="followUpForm",permission="edit")
	@RequestMapping(value="/followUpForm")
	public String getFollowUpForm(@RequestParam("selectedChildId") String selectedChildId, Model model) throws ParseException{
		model.addAttribute("selectedChild", selectedChildId);
		model.addAttribute("designation", ((UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL)).getDesignationId());
		return "followUpForm";
	}
	
	@Authorize(feature="followUpForm",permission="edit")
	@ResponseBody
	@RequestMapping(value="/getFollowUpForm")
	public List<FollowUpFormModel> getChildFollowUpForm(@RequestParam("selectedChildId") String selectedChildId, Date formFilledDate) throws Exception{
		
		return followUpFormService.getFollowUpForm(selectedChildId);
	}
	
	@ResponseBody
	@RequestMapping(value="/sendMail")
	public Boolean sendMail(@RequestBody Mail mail) throws Exception{
		return mailService.sendContactMail(mail);
	}
}
