package org.hdcd.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CodeGroup {
	
	private String groupCode;
	private String groupName;
	private String useYn;
	private LocalDateTime regDate;
	private LocalDateTime updDate;
	

}
