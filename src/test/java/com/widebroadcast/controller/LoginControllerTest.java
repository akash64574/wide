package com.widebroadcast.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.exception.InvalidLoginCredentialException;
import com.widebroadcast.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

	@InjectMocks
	LoginController loginController;

	@Mock
	UserService userService;

	LoginRequestDto loginRequestDto = new LoginRequestDto();

	@Before
	public void init() {
		loginRequestDto.setPhoneNumber(8675958381L);
		loginRequestDto.setPassword("start@123");
	}

	@Test
	public void testUserLogin() throws InvalidLoginCredentialException {
		// given
		when(userService.userLogin(loginRequestDto)).thenReturn(new LoginResponseDto());
		// when
		ResponseEntity<LoginResponseDto> response = loginController.userLogin(loginRequestDto);
		// then
		assertEquals(HttpStatus.OK.value(), response.getBody().getStatusCode());
	}

}
