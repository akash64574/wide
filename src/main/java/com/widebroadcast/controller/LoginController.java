package com.widebroadcast.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.exception.InvalidLoginCredentialException;
import com.widebroadcast.exception.UserNotFoundException;
import com.widebroadcast.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * LoginController Class - We are using this class for user activities of user
 * login purpose. As a user can login with phone number and password. A login
 * can access for admin and sales manager roles.
 * 
 * @author Govindasamy.C
 * @since 17-02-2020
 * @version v1.1
 *
 */
@RestController
@RequestMapping("/login")
@CrossOrigin
@Slf4j
public class LoginController {

	@Autowired
	UserService userService;

	/**
	 * As a user can login with phone number and password for login purpose. A login
	 * can access for admin and sales manager roles.
	 * 
	 * @param loginRequestDto - details of the username and password.
	 * @return - return the values of the userId, name and role for user.
	 * @author Govindasamy.C
	 * @throws UserNotFoundException
	 * @since 17-02-2020
	 */
	@PostMapping
	public ResponseEntity<LoginResponseDto> userLogin(@Valid @RequestBody LoginRequestDto loginRequestDto)
			throws InvalidLoginCredentialException {
		log.info("user login based on the user name and password...");
		LoginResponseDto loginResponseDto = userService.userLogin(loginRequestDto);
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		loginResponseDto.setMessage(AppConstant.LOGIN_SCCUESS_MESSAGE);
		log.info("return the response details in user login...");
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
}
