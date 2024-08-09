package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ArgumentNotFound;
import com.app.dto.ApiResponse;
import com.app.dto.UserProfileDto;
import com.app.entity.UserProfile;
import com.app.repository.UserProfileRepository;

@Service
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse createNewUserProfile(UserProfileDto userProfileDto) {
		UserProfile userProfile = mapper.map(userProfileDto, UserProfile.class);
		userProfileRepository.save(userProfile);
		return new ApiResponse("User Profile is save successfully" + userProfile.getId());
	}

	@Override
	public List<UserProfileDto> getAllUserProfList() {
		return userProfileRepository.findAll().stream()
				.map(UserProfile -> mapper.map(UserProfile, UserProfileDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserProfileDto updateUserProfile(Long id, UserProfileDto userProfileDto) {
		UserProfile userProfile = userProfileRepository.findById(id)
				.orElseThrow(() -> new ArgumentNotFound("Id is invalid!!!"));
		mapper.map(userProfileDto, userProfile);
		userProfileRepository.save(userProfile);
		return mapper.map(userProfile, UserProfileDto.class);
	}

	@Override
	public ApiResponse deleteUserProfile(Long id) {
		userProfileRepository.deleteById(id);
		return new ApiResponse("User Profile is deleted successfully" + id);
	}

}
