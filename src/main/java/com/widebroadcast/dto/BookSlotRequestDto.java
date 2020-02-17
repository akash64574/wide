package com.widebroadcast.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BookSlotRequestDto {
	
	private LocalDateTime fromDateTime;
	private LocalDateTime toDateTime;

}
