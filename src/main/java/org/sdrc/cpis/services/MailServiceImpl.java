package org.sdrc.cpis.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.sdrc.cpis.domains.EmailActivation;
import org.sdrc.cpis.models.Mail;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.repository.EmailActivationRepository;
import org.sdrc.cpis.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
	
	@Autowired
	private ResourceBundleMessageSource notificationMessageSource;
	
	@Autowired
	private EmailActivationRepository emailActivationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
//	@Autowired
//    public MailServiceImpl(ResourceBundleMessageSource notificationMessageSource) {
//        this.notificationMessageSource = notificationMessageSource;
//    }
	
	
	
	@Override
	public String sendConfirmationMail(UserDetailModel userDetailModel,Mail mail,String url,String password)  {
		try {
					 
				
				Properties props = new Properties();
				/*props.put(notificationMessageSource.getMessage(Constants.SMTP_HOST_KEY, null, null,null), notificationMessageSource.getMessage(Constants.SMTP_HOST, null, null,null));
				props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT_KEY, null, null,null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT, null, null,null));
				props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS_KEY, null, null,null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS, null, null,null));
				props.put(notificationMessageSource.getMessage(Constants.SMTP_AUTH_KEY, null, null,null), notificationMessageSource.getMessage(Constants.SMTP_AUTH, null, null,null));
				props.put(notificationMessageSource.getMessage(Constants.SMTP_PORT_KEY, null, null,null), notificationMessageSource.getMessage(Constants.SMTP_PORT, null, null,null));*/
				
				props.setProperty("mail.transport.protocol", "smtp");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "587");
				props.put("mail.smtp.socketFactory.class", "avax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "587");
				props.put("mail.smtp.starttls.enable", "true");

				javax.mail.Session session = javax.mail.Session.getDefaultInstance(
						props, new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(
										notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null,null),
										notificationMessageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null,null));
								
//								"upcpis@gmail.com",
//								"upcpisadmin");
							}
						});
				
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress());
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getToEmailIds().get(0)));
				message.setSubject("User Registration");
				
				
				 String msg = "Dear "+mail.getToUserName()+",<br><br>"+"Your profile has been created by admin and your login informations are given below : <br><br><br>"
				    		+"Your username is "+ userDetailModel.getUserName() +".<br>"
				    		+ "Your password is "+password+".<br>Please find the login link below.<br>"+url+"<br>";
				
				
				
				
				 Transport transport = session.getTransport();
				BodyPart messageBodyPart;
				
		        MimeMultipart multipart = new MimeMultipart("related");
		         messageBodyPart = new MimeBodyPart();
		         String htmlText = msg;
		        messageBodyPart.setContent(htmlText, "text/html");
		        multipart.addBodyPart(messageBodyPart);
		        
				message.setContent(multipart);
				transport.connect();
				Transport.send(message);
				transport.close();
			} catch (Exception e) {
//				System.out.println(e);
				e.printStackTrace();
				return null;
			}
			return "Done";
		}

	@Override
	public String forgotPasswordMail(Mail mail) {
			try{
				Properties props = new Properties();
				props.put(notificationMessageSource.getMessage(Constants.SMTP_HOST_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_HOST, null, null));
				props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT_KEY, null, null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT, null, null));
				props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS_KEY, null, null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS, null, null));
				props.put(notificationMessageSource.getMessage(Constants.SMTP_AUTH_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_AUTH, null, null));
				props.put(notificationMessageSource.getMessage(Constants.SMTP_PORT_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_PORT, null, null));

				javax.mail.Session session = javax.mail.Session.getDefaultInstance(
						props, new javax.mail.Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(
										notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null),
										notificationMessageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null));
							}
						});
				

				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(notificationMessageSource.getMessage(Constants.MESSAGE_SETFORM, null, null)));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getToEmailIds().get(0)));
				message.setSubject("VSL Changed Password");
				
				String msg = "Dear "+mail.getToUserName()+",<br><br>"+notificationMessageSource.getMessage("forgot.password", null,null)
		    			 +mail.getMsg()+"<br>Please use this given password to login. Find the login link below.<br>"+mail.getServerUrl();
				
				BodyPart messageBodyPart;
				
		        MimeMultipart multipart = new MimeMultipart("related");
		         messageBodyPart = new MimeBodyPart();
		         String htmlText = msg;
		        messageBodyPart.setContent(htmlText, "text/html");
		        multipart.addBodyPart(messageBodyPart);
		        
				message.setContent(multipart);
				Transport.send(message);
				
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
			return "Done";
	}
	
	public String userSignUpMail(Mail mail){
		try{
			Properties props = new Properties();
			props.put(notificationMessageSource.getMessage(Constants.SMTP_HOST_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_HOST, null, null));
			props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT_KEY, null, null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_PORT, null, null));
			props.put(notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS_KEY, null, null), notificationMessageSource.getMessage(Constants.SOCKETFACTORY_CLASS, null, null));
			props.put(notificationMessageSource.getMessage(Constants.SMTP_AUTH_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_AUTH, null, null));
			props.put(notificationMessageSource.getMessage(Constants.SMTP_PORT_KEY, null, null), notificationMessageSource.getMessage(Constants.SMTP_PORT, null, null));

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(
					props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null),
									notificationMessageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null));
						}
					});
			

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(notificationMessageSource.getMessage(Constants.MESSAGE_SETFORM, null, null)));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getToEmailIds().get(0)));
			message.setSubject("VSL Activation");
			
			String msg = "Dear "+mail.getToUserName()+",<br><br>"+notificationMessageSource.getMessage("account.activation", null, null)+"<br><br>"+mail.getServerUrl()+"/userActivation"+mail.getMsg();
			
			BodyPart messageBodyPart;
			
	        MimeMultipart multipart = new MimeMultipart("related");
	         messageBodyPart = new MimeBodyPart();
	         String htmlText = msg;
	        messageBodyPart.setContent(htmlText, "text/html");
	        multipart.addBodyPart(messageBodyPart);
	        
			message.setContent(multipart);
			Transport.send(message);
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return "Done";
	}

	
	@Override
	public String userResetPasswordMail(Mail mail) {
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.class", "avax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");

			javax.mail.Session session = javax.mail.Session.getDefaultInstance(
					props, new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null,null),
									notificationMessageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null,null));
						}
					});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null,null)));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(mail.getToEmailIds().get(0)));
			message.setSubject("CPIS Reset Password");

			String msg = "Dear " + mail.getToUserName() + ",<br><br>"
					+ "Please click on below link to change password."
					+ "<br><br>" + mail.getServerUrl() + "/resetPassword"
					+ mail.getMsg()+"<br><br><br><b> Please note Link will be active for next "
							+ "30 minutes only";
			Transport transport = session.getTransport();
			BodyPart messageBodyPart;

			MimeMultipart multipart = new MimeMultipart("related");
			messageBodyPart = new MimeBodyPart();
			String htmlText = msg;
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			transport.connect();
			Transport.send(message);
			transport.close();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "Done";
	}

	@Override
	public Integer saveMailSendInfo(Mail mail) {
		EmailActivation emailActivation=new EmailActivation();
		emailActivation.setUserId(mail.getUserId());
		Object[] email=mail.getToEmailIds().toArray();
		
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 0; i < email.length; i++) {
		   strBuilder.append(email[i]);
		}
		
		String newEmail = strBuilder.toString();
		
		emailActivation.setEmailId(newEmail);
		emailActivation.setSentDate(new Timestamp(new Date().getTime()));
		emailActivation.setIsActive(false);
		emailActivation.setIsLive(true);
		emailActivationRepository.deactivateLink(emailActivation.getUserId());
		emailActivationRepository.save(emailActivation);
		return (emailActivation.getmId());
	}
	
	@Override
	public Boolean sendContactMail(Mail mail) {
		try {
		Properties props = new Properties();
		
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "avax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		javax.mail.Session session = javax.mail.Session.getDefaultInstance(
				props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null,null),
								notificationMessageSource.getMessage(Constants.AUTHENTICATION_PASSWORD, null, null,null));
						
//						"upcpis@gmail.com",
//						"upcpisadmin");
					}
				});
		List<String> to = new ArrayList<String>();
		to.add(notificationMessageSource.getMessage(Constants.AUTHENTICATION_USERID, null, null,null));
		mail.setToEmailIds(to);
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress());
		message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail.getToEmailIds().get(0)));
		message.setSubject(mail.getSubject());
		
		
		 String msg = "From : "+mail.getFromMailId()+"<br><br>"+mail.getMessage();
		
		
		
		
		 Transport transport = session.getTransport();
		BodyPart messageBodyPart;
		
        MimeMultipart multipart = new MimeMultipart("related");
         messageBodyPart = new MimeBodyPart();
         String htmlText = msg;
        messageBodyPart.setContent(htmlText, "text/html");
        multipart.addBodyPart(messageBodyPart);
        
		message.setContent(multipart);
		transport.connect();
		Transport.send(message);
		transport.close();
	} catch (Exception e) {
		e.printStackTrace();
		return false;
	}
		return true;
	}

}
