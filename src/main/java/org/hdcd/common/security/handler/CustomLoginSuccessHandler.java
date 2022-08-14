package org.hdcd.common.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/// 로그인 성공 type c : 인증 전에 접근한 URL로 리다이렉트 하는 기능 , 기본적으로 사용됩
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication auth) throws ServletException, IOException {
		
		User customUser = (User)auth.getPrincipal();
		super.onAuthenticationSuccess(request, response,auth);
	}
	
	
	
}


//type A :  AuthenticationSuccessHandler 인터페이스를 구현하여 홈 URL("/") 로 리다이렉트
/*
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String targetUrl = "/";
		response.sendRedirect(targetUrl);
	}
}
*/

