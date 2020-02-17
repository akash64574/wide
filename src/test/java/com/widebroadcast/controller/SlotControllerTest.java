package com.widebroadcast.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

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

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.dto.SlotAvailableDto;
import com.widebroadcast.dto.SlotAvailableResponseDto;
import com.widebroadcast.dto.SlotDto;
import com.widebroadcast.dto.SlotResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.service.SlotService;

@RunWith(MockitoJUnitRunner.class)
public class SlotControllerTest {

	@InjectMocks
	SlotController slotController;

	@Mock
	SlotService slotService;

	SlotDto slotDto = new SlotDto();
	List<SlotDto> slots = new ArrayList<>();

	List<SlotAvailableDto> listOfSlots=new ArrayList<SlotAvailableDto>();
	SlotAvailableDto SlotAvailableDto=new SlotAvailableDto();
	@Before
	public void init() {
		slotDto.setProgrammeName("Super Singers");

		SlotAvailableDto.setProgramName("primimum");
		listOfSlots.add(SlotAvailableDto);
		
		slots.add(slotDto);
	}

	@Test
	public void testGetAllCreatedSlots() {
		// given
		when(slotService.getAllCreatedSlots()).thenReturn(slots);
		// when
		ResponseEntity<SlotResponseDto> response = slotController.getAllCreatedSlots();
		// then
		assertThat(response.getBody().getSlots()).hasSize(1);
	}

	@Test
	public void testCreateSlot() throws SlotNotAvailableException {
		Mockito.when(slotService.createSlot(Mockito.any())).thenReturn(new ResponseDto());
		Integer actual = slotController.createSlot(new CreateSlotRequestDto()).getStatusCodeValue();
		Integer expected = 200;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetAllSlots() {

		Mockito.when(slotService.getAllSlots()).thenReturn(listOfSlots);
		ResponseEntity<SlotAvailableResponseDto> allSlots = slotController.getAllSlots();
		assertThat(allSlots.getBody().getSlots()).hasSize(1);

	}

}
