package org.sdrc.cpis.core;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sdrc.cpis.models.FeaturePermissionMappingModel;
import org.sdrc.cpis.models.TXN_User_LogIn_Meta_Model;
import org.sdrc.cpis.models.UserDetailModel;
import org.sdrc.cpis.services.LoginService;
import org.sdrc.cpis.util.Constants;
import org.sdrc.cpis.util.StateManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthorizeInterceptor extends HandlerInterceptorAdapter {

	private final StateManager stateManager;
	private final ResourceBundleMessageSource notificationMessageSource;

	@Autowired
	public AuthorizeInterceptor(StateManager stateManager,
			ResourceBundleMessageSource notificationMessageSource) {
		this.stateManager = stateManager;
		this.notificationMessageSource = notificationMessageSource;
	}
	
	@Autowired 
	private LoginService loginService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws AccessDeniedException, NoSuchMessageException {
		
		if (!(handler instanceof HandlerMethod))
			return true;

		Authorize authorize = ((HandlerMethod) handler)
				.getMethodAnnotation(Authorize.class);

		if (authorize == null)
			return true;

		UserDetailModel user = (UserDetailModel) stateManager.getValue(Constants.USER_PRINCIPAL);
		if (user == null)
			throw new AccessDeniedException(notificationMessageSource.getMessage(
					Constants.ACCESS_DENIED, null, null));
		
		String feature = authorize.feature();
		String permission = authorize.permission();
		
		TXN_User_LogIn_Meta_Model logIn_Meta_Model =null;
//		loginService.updateLoginMeta(logIn_Meta_Model.getTxnId(), new Timestamp(new Date().getTime()));
		if(permission.equals("edit")){
			logIn_Meta_Model = loginService.getExistingUserByTxnId(user.getLoginTxnId());
			if(logIn_Meta_Model.isLive()==false)
			throw new AccessDeniedException(notificationMessageSource.getMessage(
					Constants.ACCESS_DENIED, null, null));
		}
		
		if (user != null && user.getDesignationId() != null) {
			for (int i = 0; i < user.getDesignationModel().getDesignationFeaturePermissionMappings().size(); i++) {
//				FeaturePermissionMappingModel fpMapping = user
//						.getUserRoleFeaturePermissionMappings().get(i).getRoleFeaturePermissionSchemeModel().getFeaturePermissionMapping();
				FeaturePermissionMappingModel fpMapping = user.getDesignationModel().getDesignationFeaturePermissionMappings().get(i).getFeaturePermissionMapping();
				
				if (feature.equals(fpMapping.getFeature().getFeatureName())
						&& permission.equals(fpMapping.getPermission()
								.getPermissionName())) {
					return true;
				}
			}
		}
		throw new AccessDeniedException(notificationMessageSource.getMessage(
				Constants.ACCESS_DENIED, null, null));
	}

}
