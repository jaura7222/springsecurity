package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.CodeDetail;
import org.hdcd.mapper.CodeDetailMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CodeDetailServiceImpl implements CodeDetailService {
	
	
	private final CodeDetailMapper mapper;
	
	@Override
	public void register(CodeDetail codeDetail) throws Exception {
		String groupCode = codeDetail.getGroupCode();
		int maxSortSeq = mapper.getMaxSortSeq(groupCode);
		
		codeDetail.setSortSeq(maxSortSeq +1);
		mapper.create(codeDetail);
	}

	@Override
	public CodeDetail read(CodeDetail codeDetail) throws Exception {
		return mapper.read(codeDetail);
	}

	@Override
	public void modify(CodeDetail codeDetail) throws Exception {
		mapper.update(codeDetail);
	}

	@Override
	public void remove(CodeDetail codeDetail) throws Exception {
		mapper.delete(codeDetail);
	}

	@Override
	public List<CodeDetail> list() throws Exception {
		return mapper.list();
	}

	
}
