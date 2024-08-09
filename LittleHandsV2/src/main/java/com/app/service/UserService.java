package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.UpdateUserDto;
import com.app.dto.UserDto;

public interface UserService {

	UserDto userRegistration(UserDto regDTO);

	List<UserDto> getAllUserList();

	UserDto updateUser(Long id, UpdateUserDto updateUserDto);

	ApiResponse deleteUser(Long id);
}
