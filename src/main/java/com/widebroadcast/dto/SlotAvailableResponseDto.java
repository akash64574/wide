package com.widebroadcast.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class SlotAvailableResponseDto extends ResponseDto{
	private List<SlotAvailableDto> slots;

}
