package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.CodeGroup;
import org.hdcd.mapper.CodeGroupMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeGroupServiceImpl implements CodeGroupService{
	
	
	private final CodeGroupMapper mapper;

	@Override
	public void register(CodeGroup codeGroup) throws Exception {
		mapper.create(codeGroup);
	}

	@Override
	public List<CodeGroup> list() throws Exception {
		return mapper.list();
	}

	@Override
	public CodeGroup read(String groupCode) throws Exception {
		return mapper.read(groupCode);
	}

	@Override
	public void modify(CodeGroup codeGroup) throws Exception {
		mapper.modify(codeGroup);
		
	}

	@Override
	public void remove(String groupCode) throws Exception {
		mapper.remove(groupCode);
		
	}

}
