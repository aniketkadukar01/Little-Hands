package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ChildrenDto;
import com.app.dto.UpdateChildrenDto;

public interface ChildrenService {

	ChildrenDto addNewchildren(ChildrenDto childrenDto);
	
	List<ChildrenDto> getAllChildren();
	
	UpdateChildrenDto updateChildren(Long id ,UpdateChildrenDto updateChildrenDto);
	
	ApiResponse deleteChildren(Long id);
	
}
