package org.sdrc.cpis.util;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Scope(value = "singleton")
public class SessionStateManager implements StateManager , ApplicationListener<ApplicationEvent> {	
	
	@Autowired 
	private LoginService loginService;
	
	public SessionStateManager() {
		System.out.println("In session state constructor");
	}

	@Override
	public Object getValue(String key) {
		return session().getAttribute(key);
	}

	@Override
	public void setValue(String key, Object value) {
		session().setAttribute(key, value);
	}

	

	private HttpSession session() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder
				.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

	/**
	 * This method will update the login meta data on every destruction of session
	 * Means when web.xml will destroy the session then also this method will be called
	 * OR
	 * When the server will startup to set all the lgin metadata to false or both case
	 */
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		// if session is destroying
		if(event instanceof HttpSessionDestroyedEvent)
		{
			System.out.println("Session Expired");
			UserDetailModel users=(UserDetailModel)(((HttpSessionDestroyedEvent) event).getSession().getAttribute(Constants.USER_PRINCIPAL));
		if(users!=null)
		{
			loginService.updateLoginMeta(users.getLoginTxnId(), new Timestamp(new Date().getTime()));
		}
		}
		// if server is starting up
		else if(event instanceof ContextRefreshedEvent )
		{
			loginService.updateLoginMeta(-1, new Timestamp(new Date().getTime()));
		}
		
	}
		// TODO Auto-generated method stub
		

}
