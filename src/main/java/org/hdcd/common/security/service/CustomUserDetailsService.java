package org.hdcd.common.security.service;

import org.hdcd.common.security.domain.CustomUser;
import org.hdcd.domain.Member;
import org.hdcd.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	private MemberMapper memberMapper;
	
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("Load User By username:" + username);
		
		Member member = memberMapper.readByUserId(username);
		
		log.info("queried by member mapper:" + member);
		
		return member == null ? null : new CustomUser(member);
	}
	
	

}
