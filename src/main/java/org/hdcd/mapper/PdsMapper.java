package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.hdcd.domain.Item;
import org.hdcd.domain.Pds;
import org.springframework.web.bind.annotation.PathVariable;

public interface PdsMapper {
	
	public void create(Pds item) throws Exception;
	
	public Pds read(Integer itemId) throws Exception;
	
	public void update(Pds item)throws Exception;
	
	public void delete(Integer itemId) throws Exception;
	
	public List<Pds> list() throws Exception;
	
	public void addAttach(String fullName) throws Exception;
	
	public List<String> getAttach(Integer itemId) throws Exception;
	
	public void deleteAttach(Integer itemId) throws Exception;
	
	public void replaceAttach(@Param("fullName") String fullName, @Param("itemId") Integer itemId)  throws Exception;
	
	public void updateAttachDownCnt(String fullName) throws Exception;
	
	public void updateViewCnt(Integer itemId) throws Exception;

}
