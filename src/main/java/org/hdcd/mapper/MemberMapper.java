package org.hdcd.mapper;

import org.hdcd.domain.Member;
import org.hdcd.domain.MemberAuth;

public interface MemberMapper {
	
	public void create(Member member) throws Exception;
	
	public void createAuth(MemberAuth memberAuth) throws Exception;
	
	public Member readByUserId(String userId);

}
