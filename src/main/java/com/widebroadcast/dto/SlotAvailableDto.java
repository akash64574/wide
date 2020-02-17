package com.widebroadcast.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotAvailableDto {
	private String programName;
	private String plantype;
	private Double price;
	private List<TimeSlotDto> timeShots;
	private LocalDate slotDate;
}
