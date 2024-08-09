package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SigninRequest;
import com.app.dto.SigninResponse;
import com.app.dto.UpdateUserDto;
import com.app.dto.UserDto;
import com.app.security.JwtUtils;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private AuthenticationManager authMgr;

	@Autowired
	private UserService userService;
	
	// sign up
		/*
		 * URL - http://host:port/users/signup 
		 * Method - POST 
		 * request payload : sign up
		 * DTO (user details) resp : In case of success : Auth Resp DTO :mesg + JWT
		 * token + SC 201 In case of failure : SC 401
		 * 
		 */
		@PostMapping("/signup")
		public ResponseEntity<?> userSignup(@RequestBody @Valid UserDto dto) {
			System.out.println("in sign up " + dto);
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(userService.userRegistration(dto));
		}
		
	/*
	 * URL - http://host:port/users/signin Method - POST request payload : Auth req
	 * DTO : email n password resp : In case of success : Auth Resp DTO :mesg + JWT
	 * token + SC 201 In case of failure : SC 401
	 * 
	 */
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid SigninRequest request) {
		System.out.println("in sign in" + request);
		// 1. create a token(implementation of Authentication i/f)
		// to store un verified user email n pwd
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(),
				request.getPassword());
		//2.  invoke auth mgr's authenticate method;
		Authentication verifiedToken = authMgr.authenticate(token);
			// => authentication successful !
			//3. In case of successful auth,  create JWT n send it to the clnt in response
		SigninResponse resp = new SigninResponse(jwtUtils.generateJwtToken(verifiedToken), "Successful Auth!!!!");
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}

	@GetMapping("/getallusers")
	public ResponseEntity<?> getAllUserProfList() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.getAllUserList());
	}

	@PutMapping("/updateuser/{id}")
	public ResponseEntity<?> updateUserProfile(@PathVariable Long id,
			@Valid @RequestBody UpdateUserDto userDto) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.updateUser(id, userDto));
	}

	@DeleteMapping("/deleteuser/{id}")
	public ResponseEntity<?> deleteUserProfile(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(userService.deleteUser(id));
	}
	
}
