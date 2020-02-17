package com.widebroadcast.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotDto {

	private String programmeName;
	private String planType;
	private Double price;
	private LocalDate slotFromDate;
	private LocalTime slotFromTime;
	private LocalDate slotToDate;
	private LocalTime slotToTime;
	private String status;
}
