package com.widebroadcast.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.widebroadcast.common.WideBroadCastEnum.TimeSlotStatus;
import com.widebroadcast.constant.AppConstant;
import com.widebroadcast.dto.BookSlotRequestDto;
import com.widebroadcast.dto.LoginRequestDto;
import com.widebroadcast.dto.LoginResponseDto;
import com.widebroadcast.dto.ResponseDto;
import com.widebroadcast.entity.TimeSlot;
import com.widebroadcast.entity.User;
import com.widebroadcast.exception.InvalidLoginCredentialException;
import com.widebroadcast.exception.SlotNotAvailableException;
import com.widebroadcast.exception.UserNotFoundException;
import com.widebroadcast.repository.TimeSlotRepository;
import com.widebroadcast.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * UserServiceImpl Class - We are using this class for user activities of user
 * login purpose. As a user can login with phone number and password. A login
 * can access for admin and sales manager roles.
 * 
 * @author Govindasamy.C
 * @since 17-02-2020
 * @version V1.1
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	TimeSlotRepository timeSlotRepository;

	/**
	 * As a user can login with phone number and password for login purpose. A login
	 * can access for admin and sales manager roles.
	 * 
	 * @param loginRequestDto - details of the username and password.
	 * @return - return the values of the userId, name and role for user.
	 * @author Govindasamy.C
	 * @throws UserNotFoundException
	 * @since 17-02-2020
	 */
	@Override
	public LoginResponseDto userLogin(LoginRequestDto loginRequestDto) throws InvalidLoginCredentialException {
		log.info("user login based on the user name and password...");
		Optional<User> user = userRepository.findByPhoneNumberAndPassword(loginRequestDto.getPhoneNumber(),
				loginRequestDto.getPassword());
		if (!user.isPresent()) {
			throw new InvalidLoginCredentialException(AppConstant.INVALID_LOGIN_CREDENTCIAL);
		}
		log.info("setting the responsse details of the user login...");
		LoginResponseDto loginResponseDto = new LoginResponseDto();
		BeanUtils.copyProperties(user.get(), loginResponseDto);
		log.info("return the response details in user login...");
		return loginResponseDto;
	}

	/**
	 * As a slaes person can book the slots between the available timeline
	 * 
	 * @param createSlotRequestDto - contains fromDateTime, toDateTime, planId, and
	 *                             programName.
	 * @return - success message and status code with Http response
	 * @author Raghu M
	 * @throws SlotNotAvailableException
	 * @since 17-02-2020
	 */
	@Override
	public ResponseDto bookSlots(Integer userId, BookSlotRequestDto bookSlotRequestDto)
			throws SlotNotAvailableException {
		log.info("UserServiceImpl bookSlots ---> booking slot");
		List<TimeSlot> timeSlots = timeSlotRepository.findByStatusAndSlotDateTimeBetween(TimeSlotStatus.AVAILABLE,
				bookSlotRequestDto.getFromDateTime(), bookSlotRequestDto.getToDateTime());
		Integer count = timeSlots.size();
		int expectedCount = 0;
		while (bookSlotRequestDto.getFromDateTime().isBefore(bookSlotRequestDto.getToDateTime().plusSeconds(1))) {
			expectedCount++;
			bookSlotRequestDto.setFromDateTime(bookSlotRequestDto.getFromDateTime().plusSeconds(1));
		}
		if (expectedCount != count) {
			log.info("UserServiceImpl bookSlots ---> SlotNotAvailableException occured");
			throw new SlotNotAvailableException(AppConstant.SLOT_NOT_AVAILABLE);
		}
		timeSlots.forEach(timeSlot -> {
			timeSlot.setStatus(TimeSlotStatus.BOOKED);
			User user = new User();
			user.setUserId(userId);
			timeSlot.setUser(user);
		});
		timeSlotRepository.saveAll(timeSlots);
		log.info("UserServiceImpl bookSlots ---> slots booked");
		return new ResponseDto();
	}

}
