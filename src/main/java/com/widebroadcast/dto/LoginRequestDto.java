package com.widebroadcast.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.widebroadcast.constant.AppConstant;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LoginRequestDto {

	@Digits(fraction = 0, integer = 10, message = AppConstant.PHONE_NUMBER_SHOULD_BE_LENGTH)
	private Long phoneNumber;

	@NotNull(message = AppConstant.CREDENTIAL_SHOULD_BE_NOT_NULL)
	private String password;
}
