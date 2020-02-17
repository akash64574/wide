package com.widebroadcast.service;

import com.widebroadcast.dto.BookSlotRequestDto;
import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.exception.InvalidLoginCredentialException;
import com.widebroadcast.exception.SlotNotAvailableException;

public interface UserService {

	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidLoginCredentialException;

	ResponseDto bookSlots(Integer userId, BookSlotRequestDto bookSlotRequestDto) throws SlotNotAvailableException;

}
