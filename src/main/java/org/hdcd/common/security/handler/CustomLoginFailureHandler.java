package org.hdcd.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomLoginFailureHandler implements AuthenticationFailureHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest == null) response.sendRedirect("/");
		else {
			String targetUrl = savedRequest.getRedirectUrl();
			response.sendRedirect(targetUrl);
		}
		
		
	}
	
}
