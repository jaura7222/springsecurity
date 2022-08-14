package org.hdcd.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {
	
	
	private int noticeNo;
	private String title;
	private String content;
	private LocalDateTime regDate;

}
