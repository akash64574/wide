package com.widebroadcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.dto.SlotAvailableDto;
import com.widebroadcast.dto.SlotAvailableResponseDto;
import com.widebroadcast.dto.SlotDto;
import com.widebroadcast.dto.SlotResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.service.SlotService;

import lombok.extern.slf4j.Slf4j;

/**
 * SlotController Class - we can use the get the all slots for admin and create
 * a new slot by admin.
 * 
 * @author Govindasamy.C
 * @since 17-02-2020
 * @version V1.1
 *
 */
@RestController
@RequestMapping("/slots")
@CrossOrigin
@Slf4j
public class SlotController {

	@Autowired
	SlotService slotService;

	/**
	 * As a admin can create slots for the ads
	 * 
	 * @param createSlotRequestDto - contains fromDateTime, toDateTime, planId, and
	 *                             programName.
	 * @return - success message and status code with Http response
	 * @author Raghu M
	 * @throws SlotNotAvailableException
	 * @since 17-02-2020
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> createSlot(@RequestBody CreateSlotRequestDto createSlotRequestDto)
			throws SlotNotAvailableException {
		log.info("SlotController createSlot ---> creatinf slots ");
		ResponseDto responseDto = slotService.createSlot(createSlotRequestDto);
		responseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		responseDto.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok().body(responseDto);
	}

	/**
	 * Get all slots by admin
	 * 
	 * @return response details for list of slots along with status code and
	 *         message.
	 * @author Govindasamy.C
	 * @since 17-02-2020
	 */
	@GetMapping
	public ResponseEntity<SlotResponseDto> getAllCreatedSlots() {
		log.info("get all the created slots for admin...");
		List<SlotDto> slotDtos = slotService.getAllCreatedSlots();
		SlotResponseDto slotResponseDto = new SlotResponseDto();
		slotResponseDto.setSlots(slotDtos);
		slotResponseDto.setStatusCode(HttpStatus.OK.value());
		slotResponseDto.setMesssage(AppConstant.SUCCESS_MESSAGE);
		log.info("return the response details for get all slots...");
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);
	}

	@GetMapping("/available")
	public ResponseEntity<SlotAvailableResponseDto> getAllSlots() {
		log.info("starting getAllPlan method , inside plantypecontroller ...");
		List<SlotAvailableDto> allSlots = slotService.getAllSlots();

		SlotAvailableResponseDto slotResponseDto = new SlotAvailableResponseDto();
		slotResponseDto.setStatusCode(HttpStatus.OK.value());
		slotResponseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		slotResponseDto.setSlots(allSlots);
		log.info("return the response details ...");
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);
	}

}
