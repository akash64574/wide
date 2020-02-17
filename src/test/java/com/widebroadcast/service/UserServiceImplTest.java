package com.widebroadcast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.widebroadcast.dto.BookSlotRequestDto;
import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.entity.Slot;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.entity.User;
import com.widebroadcast.exception.InvalidLoginCredentialException;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.repository.TimeSlotRepository;
import com.widebroadcast.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	TimeSlotRepository timeSlotRepository;

	TimeSlot timeSlot = new TimeSlot();
	List<TimeSlot> timeSlots = new ArrayList<>();

	LoginRequestDto loginRequestDto = new LoginRequestDto();
	User user = new User();
	
	BookSlotRequestDto bookSlotRequestDto = new BookSlotRequestDto();

	@Before
	public void init() {
		loginRequestDto.setPhoneNumber(8675958381L);
		loginRequestDto.setPassword("start@123");
		user.setUserId(1);
		Slot slot = new Slot();
		slot.setSlotId(1);
		timeSlot.setSlot(slot);
		timeSlot.setSlotDateTime(LocalDateTime.now());
		timeSlot.setStatus(TimeSlotStatus.AVAILABLE);
		timeSlot.setTimeSlotId(1);
		timeSlot.setUser(user);
		timeSlots.add(timeSlot);
		
		bookSlotRequestDto.setFromDateTime(LocalDateTime.now());
		bookSlotRequestDto.setToDateTime(LocalDateTime.now());
	}

	@Test
	public void testUserLogin() throws InvalidLoginCredentialException {
		when(userRepository.findByPhoneNumberAndPassword(loginRequestDto.getPhoneNumber(),
				loginRequestDto.getPassword())).thenReturn(Optional.of(user));
		LoginResponseDto response = userServiceImpl.userLogin(loginRequestDto);
		assertEquals(1, response.getUserId());
	}

	@Test(expected = InvalidLoginCredentialException.class)
	public void testUserLoginForUserNotFoundEx() throws InvalidLoginCredentialException {
		when(userRepository.findByPhoneNumberAndPassword(loginRequestDto.getPhoneNumber(),
				loginRequestDto.getPassword())).thenReturn(Optional.ofNullable(null));
		userServiceImpl.userLogin(loginRequestDto);
	}

	@Test(expected = SlotNotAvailableException.class)
	public void testBookSlotsSlotNotAvailableException() throws SlotNotAvailableException {
		Mockito.when(timeSlotRepository.findByStatusAndSlotDateTimeBetween(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(new ArrayList<>());
		userServiceImpl.bookSlots(1, bookSlotRequestDto);

	}

	@Test
	public void testBookSlotsSuccess() throws SlotNotAvailableException {
		Mockito.when(timeSlotRepository.findByStatusAndSlotDateTimeBetween(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(timeSlots);
		Mockito.when(timeSlotRepository.saveAll(Mockito.any())).thenReturn(new ArrayList<TimeSlot>());
		assertNotNull(userServiceImpl.bookSlots(1, bookSlotRequestDto));

	}

}
