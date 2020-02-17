package com.widebroadcast.service;

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

import com.widebroadcast.dto.PlanDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.repository.PlanTypeRepository;

@RunWith(MockitoJUnitRunner.class)
public class PlanTypeServiceImplTest {

	@InjectMocks
	PlanTypeServiceImpl planTypeServiceImpl;
	@Mock
	PlanTypeRepository planTypeRepository;
	List<PlanType> plantype = new ArrayList<PlanType>();
	PlanType planType = new PlanType();

	@Before
	public void setup() {
		planType.setPlanTypeName("primimum");
		plantype.add(planType);
	}

	@Test
	public void testGetAllPlans() {

		Mockito.when(planTypeRepository.findAll()).thenReturn(plantype);

		List<PlanDto> allPlans = planTypeServiceImpl.getAllPlans();

		assertThat(allPlans).hasSize(1);

	}

}
