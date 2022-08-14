package org.hdcd.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	private int userNo;
	
	@NotBlank
	private String userId;
	
	@NotBlank
	private String userPw;
	
	@NotBlank
	private String userName;
	
	private String job;
	private int coin;
	
	private boolean enabled;
	
	private LocalDateTime regDate;
	private LocalDateTime updDate;
	
	private List<MemberAuth> authList;

}
