package com.widebroadcast.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.BookSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Get the user activities such as create a new user and approval stage.
 * 
 * @author Amala.S
 * @since 15-02-2020
 * @version V1.1
 *
 */
@RestController
@RequestMapping("/users")
@CrossOrigin
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * As a slaes person can book the slots between the available timeline
	 * 
	 * @param createSlotRequestDto - contains fromDateTime, toDateTime, planId, and
	 *                             programName.
	 * @return - success message and status code with Http response
	 * @author Raghu M
	 * @throws SlotNotAvailableException
	 * @since 17-02-2020
	 */
	@PostMapping("{userId}/slots")
	public ResponseEntity<ResponseDto> bookslots(@PathVariable("userId") Integer userId,
			@RequestBody BookSlotRequestDto bookSlotRequestDto) throws SlotNotAvailableException {
		log.info("UserController bookslots ---> booking slots");
		ResponseDto responseDto = userService.bookSlots(userId, bookSlotRequestDto);
		responseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		responseDto.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok().body(responseDto);
	}

}
