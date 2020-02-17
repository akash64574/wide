package com.widebroadcast.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class PlanType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer planTypeId;
	private String planTypeName;
	private Double price;

}
