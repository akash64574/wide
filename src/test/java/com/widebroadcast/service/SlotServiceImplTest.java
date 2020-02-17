package com.widebroadcast.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.widebroadcast.common.WideBroadCastEnum.TimeSlotStatus;
import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.SlotAvailableDto;
import com.widebroadcast.dto.SlotDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.entity.Slot;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.repository.SlotRepository;
import com.widebroadcast.repository.TimeSlotRepository;

@RunWith(MockitoJUnitRunner.class)
public class SlotServiceImplTest {

	@InjectMocks
	SlotServiceImpl slotServiceImpl;

	@Mock
	TimeSlotRepository timeSlotRepository;

	@Mock
	SlotRepository slotRepository;
	
	Slot slot = new Slot();
	List<Slot> slots = new ArrayList<>();
	TimeSlot timeSlot = new TimeSlot();
	List<TimeSlot> timeSlots = new ArrayList<>();
	PlanType planType = new PlanType();
	CreateSlotRequestDto createSlotRequestDto = new CreateSlotRequestDto();

	@Before
	public void init() {
		createSlotRequestDto.setPlanTypeId(1);
		createSlotRequestDto.setProgrammeName("test");
		createSlotRequestDto.setSlotFromDateTime(LocalDateTime.now());
		createSlotRequestDto.setSlotToDateTime(LocalDateTime.now());
		
		slot.setSlotId(1);
		slot.setProgramName("Super Singers");
		
		planType.setPlanTypeId(1);
		
		timeSlot.setSlot(slot);
		timeSlot.setSlotDateTime(LocalDateTime.now());
		timeSlot.setTimeSlotId(1);
		timeSlot.setStatus(TimeSlotStatus.AVAILABLE);
		timeSlots.add(timeSlot);
		
		slot.setPlantype(planType);
		slot.setTimeSlots(timeSlots);
		
		slots.add(slot);
	}
	
	@Test
	public void testGetAllCreatedSlots() {
		//given
		when(slotRepository.findAll()).thenReturn(slots);
		//when
		List<SlotDto> slotDtos = slotServiceImpl.getAllCreatedSlots();
		//then
		assertThat(slotDtos).hasSize(1);
	}
	
	@Test(expected = SlotNotAvailableException.class)
	public void testCreateSlotSlotNotAvailableException() throws SlotNotAvailableException {
		Mockito.when(timeSlotRepository.findBySlotDateTime(Mockito.any())).thenReturn(Optional.ofNullable(new TimeSlot()));
		slotServiceImpl.createSlot(createSlotRequestDto);
	}
	
	@Test
	public void testCreateSlotSuccess() throws SlotNotAvailableException {
		Mockito.when(timeSlotRepository.findBySlotDateTime(Mockito.any())).thenReturn(Optional.ofNullable(null));
		Mockito.when(slotRepository.save(Mockito.any())).thenReturn(slot);
		Mockito.when(timeSlotRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<>());
		assertNotNull(slotServiceImpl.createSlot(createSlotRequestDto));
	}
	
	@Test
	public void testGetAllSlots() {

		Mockito.when(slotRepository.findAll()).thenReturn(slots);
		List<SlotAvailableDto> allSlots = slotServiceImpl.getAllSlots();

		assertThat(allSlots).hasSize(1);

	}
	

}
