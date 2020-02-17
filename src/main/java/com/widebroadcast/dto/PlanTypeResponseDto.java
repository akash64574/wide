package com.widebroadcast.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PlanTypeResponseDto {
	private Integer statusCode;
	private String message;
	
	private List<PlanDto> plans;

}
