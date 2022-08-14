package org.hdcd.service;

import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;
import org.hdcd.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberSerivceImpl implements MemberService {
	
	private final MemberMapper mapper;

	@Transactional
	@Override
	public void register(Member member) throws Exception {
		mapper.create(member);
		
		MemberAuth memberAuth = new MemberAuth();
		memberAuth.setUserNo(member.getUserNo());
		memberAuth.setAuth("ROLE_MEMBER");
		mapper.createAuth(memberAuth);
		
	}
	
	

}
