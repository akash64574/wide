package com.widebroadcast.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeSlotDto {

	private LocalTime availableTime;
}
