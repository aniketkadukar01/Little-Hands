package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ChildrenDto;

public interface ChildrenService {

	ChildrenDto addNewchildren(ChildrenDto childrenDto);
	
	List<ChildrenDto> getAllChildren();
	
	ChildrenDto updateChildren(Long id ,ChildrenDto childrenDto);
	
	ApiResponse deleteChildren(Long id);
	
}
