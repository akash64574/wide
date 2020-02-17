package com.widebroadcast.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotResponseDto {
	
	private Integer statusCode;
	private String messsage;
	private List<SlotDto> slots;
}
