package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ArgumentNotFound;
import com.app.dto.ApiResponse;
import com.app.dto.ChildrenDto;
import com.app.entity.Children;
import com.app.entity.User;
import com.app.repository.ChildrenRepository;
import com.app.repository.UserRepository;

@Service
@Transactional
public class ChildrenServiceImpl implements ChildrenService {

	@Autowired
	private ChildrenRepository childrenRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ChildrenDto addNewchildren(ChildrenDto childrenDto) {
		User user = userRepository.findById(childrenDto.getUserId())
				.orElseThrow(() -> new ArgumentNotFound("User ID is Invalid!!!"));
		Children children = mapper.map(childrenDto, Children.class);
		children.setUser(user);
		childrenRepository.save(children);
		return mapper.map(children, childrenDto.getClass());
	}

	@Override
	public List<ChildrenDto> getAllChildren() {
		return childrenRepository.findAll().stream()
				.map(Children -> mapper.map(Children, ChildrenDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public ChildrenDto updateChildren(Long id, ChildrenDto childrenDto) {
		Children children = childrenRepository.findById(id)
				.orElseThrow(() -> new ArgumentNotFound("Id is Invalid!!!"));
		mapper.map(childrenDto, children);
		childrenRepository.save(children);
		return mapper.map(children, ChildrenDto.class);
	}

	@Override
	public ApiResponse deleteChildren(Long id) {
		childrenRepository.deleteById(id);
		return new ApiResponse("Children is deleted successfully with id " + id);
	}

}
