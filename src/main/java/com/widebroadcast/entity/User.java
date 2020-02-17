package com.widebroadcast.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.widebroadcast.common.WideBroadCastEnum.Role;

import lombok.Data;

@Data
@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	private String name;
	private String emailAddress;
	@Column(unique=true)
	private Long phoneNumber;
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;

}
