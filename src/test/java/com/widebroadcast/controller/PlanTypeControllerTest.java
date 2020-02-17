package com.widebroadcast.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.widebroadcast.dto.PlanDto;
import com.widebroadcast.dto.PlanTypeResponseDto;
import com.widebroadcast.service.PlanTypeService;

@RunWith(MockitoJUnitRunner.class)
public class PlanTypeControllerTest {

	@InjectMocks
	PlanTypeController planTypeController;

	@Mock
	PlanTypeService planTypeService;
	List<PlanDto> plans = new ArrayList<PlanDto>();
	PlanDto planDto = new PlanDto();

	@Before
	public void setup() {
		planDto.setPlanTypeName("primimum");
		plans.add(planDto);
	}

	@Test
	public void testGetAllPlans() {

		Mockito.when(planTypeService.getAllPlans()).thenReturn(plans);

		ResponseEntity<PlanTypeResponseDto> allPlans = planTypeController.getAllPlans();

		assertThat(allPlans.getBody().getPlans()).hasSize(1);
	}

}
