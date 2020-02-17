package com.widebroadcast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.common.WideBroadCastEnum.TimeSlotStatus;
import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.CreateSlotRequestDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.dto.SlotAvailableDto;
import com.widebroadcast.dto.SlotDto;
import com.widebroadcast.dto.TimeSlotDto;
import com.widebroadcast.entity.PlanType;
import com.widebroadcast.entity.Slot;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.repository.SlotRepository;
import com.widebroadcast.repository.TimeSlotRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class SlotServiceImpl implements SlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Autowired
	SlotRepository slotRepository;

	/**
	 * As a admin can create slots for the ads
	 * 
	 * @param createSlotRequestDto - contains fromDateTime, toDateTime, planId, and
	 *                             programName.
	 * @return - success message and status code with Http response
	 * @author Raghu M
	 * @throws SlotNotAvailableException
	 * @since 17-02-2020
	 */
	@Override
	public ResponseDto createSlot(CreateSlotRequestDto createSlotRequestDto) throws SlotNotAvailableException {
		log.info("SlotServiceImpl createSlot ---> creating slots");
		if (timeSlotRepository.findBySlotDateTime(createSlotRequestDto.getSlotFromDateTime()).isPresent()) {
			log.info("SlotServiceImpl createSlot ---> SlotNotAvailableException occured");
			throw new SlotNotAvailableException(AppConstant.SLOT_ALREADY_CREATED);
		}
		PlanType plantype = new PlanType();
		plantype.setPlanTypeId(createSlotRequestDto.getPlanTypeId());
		Slot slot = new Slot();
		slot.setPlantype(plantype);
		slot.setProgramName(createSlotRequestDto.getProgrammeName());
		slot.setStatus(AppConstant.SLOT_AVAILABLE);
		slot = slotRepository.save(slot);
		List<TimeSlot> timeSlots = new ArrayList<>();
		while (createSlotRequestDto.getSlotFromDateTime()
				.isBefore(createSlotRequestDto.getSlotToDateTime().plusSeconds(1))) {
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setSlot(slot);
			timeSlot.setSlotDateTime(createSlotRequestDto.getSlotFromDateTime());
			timeSlot.setStatus(TimeSlotStatus.AVAILABLE);
			createSlotRequestDto.setSlotFromDateTime(createSlotRequestDto.getSlotFromDateTime().plusSeconds(1));
			timeSlots.add(timeSlot);
		}
		timeSlotRepository.saveAll(timeSlots);
		log.info("SlotServiceImpl createSlot ---> slots created");
		return new ResponseDto();
	}

	/**
	 * Get all slots by admin
	 * 
	 * @return response details for list of slots along with status code and
	 *         message.
	 * @author Govindasamy.C
	 * @since 17-02-2020
	 */
	@Override
	public List<SlotDto> getAllCreatedSlots() {
		log.info("get all the created slots for admin...");
		List<Slot> slots = slotRepository.findAll();
		List<SlotDto> slotDtos = new ArrayList<>();
		slots.forEach(slot -> {
			SlotDto slotDto = new SlotDto();
			log.info("get all the time slots by slot...");
			List<TimeSlot> timeSlots = slot.getTimeSlots();
			BeanUtils.copyProperties(slot.getPlantype(), slotDto);

			log.info("check timeshots size...");
			if (!timeSlots.isEmpty()) {
				TimeSlot fromTimeSlot = timeSlots.stream().findFirst().get();
				BeanUtils.copyProperties(slot, slotDto);
				slotDto.setSlotFromDate(fromTimeSlot.getSlotDateTime().toLocalDate());
				slotDto.setSlotFromTime(fromTimeSlot.getSlotDateTime().toLocalTime());

				TimeSlot lastTimeSlot = timeSlots.get(timeSlots.size() - 1);
				slotDto.setSlotToDate(lastTimeSlot.getSlotDateTime().toLocalDate());
				slotDto.setSlotToTime(lastTimeSlot.getSlotDateTime().toLocalTime());
			}
			log.info("setting values to to slotdto for response...");
			slotDto.setPlanType(slot.getPlantype().getPlanTypeName());
			slotDto.setProgrammeName(slot.getProgramName());
			slotDtos.add(slotDto);
		});
		log.info("return the list of slots by response...");
		return slotDtos;
	}

	@Override
	public List<SlotAvailableDto> getAllSlots() {

		List<Slot> slots = slotRepository.findAll();
		List<SlotAvailableDto> availaSlots = new ArrayList<>();
		slots.forEach(slot -> {
			SlotAvailableDto slotAvailableDto = new SlotAvailableDto();
			slotAvailableDto.setPlantype(slot.getPlantype().getPlanTypeName());
			slotAvailableDto.setProgramName(slot.getProgramName());
			slotAvailableDto.setPrice(slot.getPlantype().getPrice());
			List<TimeSlot> timeShots = slot.getTimeSlots();
			TimeSlot firstTimeShot = timeShots.stream().findFirst().get();
			slotAvailableDto.setSlotDate(firstTimeShot.getSlotDateTime().toLocalDate());
			List<TimeSlotDto> timeSlots = timeShots.stream()
					.filter(timeShot -> timeShot.getStatus().equals(TimeSlotStatus.AVAILABLE))
					.map(this::convertSlotEntityToDto).collect(Collectors.toList());
			slotAvailableDto.setTimeShots(timeSlots);
			availaSlots.add(slotAvailableDto);
		});

		return availaSlots;
	}

	private TimeSlotDto convertSlotEntityToDto(TimeSlot timeSlot) {
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		timeSlotDto.setAvailableTime(timeSlot.getSlotDateTime().toLocalTime());
		return timeSlotDto;
	}

}
