package com.widebroadcast.dto;

import com.widebroadcast.common.WideBroadCastEnum.Role;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {

	private Integer statusCode;
	private String message;
	private Integer userId;
	private Role role;
	private String name;

}
