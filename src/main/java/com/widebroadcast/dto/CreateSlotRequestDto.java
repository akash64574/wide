package com.widebroadcast.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateSlotRequestDto {
	
	private String programmeName;
	private LocalDateTime slotFromDateTime;
	private LocalDateTime slotToDateTime;
	private Integer planTypeId;

}
