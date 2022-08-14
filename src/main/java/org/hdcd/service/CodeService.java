package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.CodeLabelValue;

public interface CodeService {
	
	public List<CodeLabelValue> getCodeGroupList() throws Exception;
	
	public List<CodeLabelValue> getCodeList(String groupCode) throws Exception;

}
