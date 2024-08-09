package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exception.ArgumentNotFound;
import com.app.dto.ApiResponse;
import com.app.dto.UpdateUserDto;
import com.app.dto.UserDto;
import com.app.entity.User;
import com.app.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDto userRegistration(UserDto regDTO) {

		if (userRepository.existsByEmail(regDTO.getEmail()))
			throw new ArgumentNotFound("Email already exists !!!");

		// dto --> entity
		User user = mapper.map(regDTO, User.class);

		user.setPassword(encoder.encode(user.getPassword()));// pwd : encrypted using SHA

		return mapper.map(userRepository.save(user), UserDto.class);
	}

	@Override
	public List<UserDto> getAllUserList() {
		return userRepository.findAll().stream().map(User -> mapper.map(User, UserDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(Long id, UpdateUserDto updateUserDto) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ArgumentNotFound("Id is invalid!!!"));
		updateUserDto.setPassword(encoder.encode(updateUserDto.getPassword()));
		mapper.map(updateUserDto, user);
		userRepository.save(user);
		return mapper.map(user, UserDto.class);
	}

	@Override
	public ApiResponse deleteUser(Long id) {
		userRepository.deleteById(id);
		return new ApiResponse("User Profile is deleted successfully with id " + id);
	}

}
