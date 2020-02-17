package com.widebroadcast.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.widebroadcast.common.WideBroadCastEnum.TimeSlotStatus;

import lombok.Data;

@Data
@Entity
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer timeSlotId;
	private LocalDateTime slotDateTime;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "slot_id")
	private Slot slot;
	@Enumerated(EnumType.STRING)
	private TimeSlotStatus status;

}
