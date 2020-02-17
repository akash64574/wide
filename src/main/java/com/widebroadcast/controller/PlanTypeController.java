package com.widebroadcast.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.PlanDto;
import com.widebroadcast.dto.PlanTypeResponseDto;
import com.widebroadcast.service.PlanTypeService;

import lombok.extern.slf4j.Slf4j;

/**
 *  PlanTypeController this controller is used to Get the all plans 
 * 
 * @author Amala.S
 * @since 15-02-2020
 * @version V1.1
 *
 */
@RestController
@RequestMapping("/plans")
@CrossOrigin
@Slf4j
public class PlanTypeController {

	@Autowired
	PlanTypeService planTypeService;

	@GetMapping
	public ResponseEntity<PlanTypeResponseDto> getAllPlans() {
		log.info("starting getAllPlan method , inside plantypecontroller ...");
		List<PlanDto> allPlans = planTypeService.getAllPlans();

		PlanTypeResponseDto planTypeResponseDto = new PlanTypeResponseDto();
		planTypeResponseDto.setPlans(allPlans);
		planTypeResponseDto.setStatusCode(HttpStatus.OK.value());
		planTypeResponseDto.setMessage(AppConstant.SUCCESS_MESSAGE);
		log.info("return the response details ...");
		return new ResponseEntity<>(planTypeResponseDto, HttpStatus.OK);
	}

}
