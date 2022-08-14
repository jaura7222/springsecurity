package org.hdcd.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class CodeLabelValue {
	
	
	private final String value;
	private final String label;

}
