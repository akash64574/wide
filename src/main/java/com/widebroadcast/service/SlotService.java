package com.widebroadcast.service;

import java.util.List;

import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.dto.SlotAvailableDto;
import com.widebroadcast.dto.SlotDto;
import com.widebroadcast.exception.SlotNotAvailableException;

public interface SlotService {

	ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) throws SlotNotAvailableException;

	List<SlotDto> getAllCreatedSlots();

	List<SlotAvailableDto> getAllSlots();

}
