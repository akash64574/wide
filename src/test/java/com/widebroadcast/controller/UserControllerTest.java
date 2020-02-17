package com.widebroadcast.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.widebroadcast.dto.BookSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	
	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;
	
	@Test
	public void testBookslotsSuccess() throws SlotNotAvailableException {
		Mockito.when(userService.bookSlots(Mockito.any(), Mockito.any())).thenReturn(new ResponseDto());
		Integer actual = userController.bookslots(1, new BookSlotRequestDto()).getStatusCodeValue();
		Integer expected = 200;
		assertEquals(expected, actual);
	}

}
