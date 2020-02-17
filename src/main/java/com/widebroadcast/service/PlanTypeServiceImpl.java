package com.widebroadcast.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.dto.PlanDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.repository.PlanTypeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * PlanTypeServiceImpl Class - We are using this class to get the plan details
 * 
 * 
 * 
 * @author Amala.S
 * @since 17-02-2020
 * @version V1.1
 *
 */
@Service
@Slf4j
public class PlanTypeServiceImpl implements PlanTypeService {
	@Autowired
	PlanTypeRepository planTypeRepository;

	@Override
	public List<PlanDto> getAllPlans() {
		log.info("starting getAllPlan method , inside PlanTypeServiceImpl ...");
		List<PlanType> findAll = planTypeRepository.findAll();
		return findAll.stream().map(this::convertEntityToDto).collect(Collectors.toList());
	}

	private PlanDto convertEntityToDto(PlanType planType) {

		PlanDto plan = new PlanDto();
		plan.setPlanTypeId(planType.getPlanTypeId());
		plan.setPlanTypeName(planType.getPlanTypeName());
		plan.setPrice(planType.getPrice());

		return plan;

	}

}
