package org.sdrc.cpis.services;


import org.sdrc.cpis.models.Mail;
import org.sdrc.cpis.models.UserDetailModel;

public interface MailService {

	
	String sendConfirmationMail(UserDetailModel userDetailModel,Mail mail,String url,String password);
	
	String forgotPasswordMail(Mail mail);
	
	String userSignUpMail(Mail mail);
	
	String userResetPasswordMail(Mail mail);

	Integer saveMailSendInfo(Mail mail);

	Boolean sendContactMail(Mail mail);

}
