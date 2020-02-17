package com.widebroadcast.constant;

/**
 * Set the all constant values and use that values in the whole application
 * 
 * @author Amala.S
 * @since 17-02-2020
 * @version V1.1
 *
 */
public class AppConstant {

	private AppConstant() {

	}

	// Common
	public static final String FAILURE_MESSAGE = "FAILURE";
	public static final String SUCCESS_MESSAGE = "SUCCESS";
	public static final String NO_RECORDS_FOUND = "No Records Found";

	// User
	public static final String INVALID_LOGIN_CREDENTCIAL = "Invalid Login Credential";
	public static final String USER_NOT_FOUND = "User not found";
	public static final String LOGIN_SCCUESS_MESSAGE = "User Login Successfully";

	// Login
	public static final String PHONE_NUMBER_SHOULD_BE_LENGTH = "Phone Number shoulb be 10 digits";
	public static final String CREDENTIAL_SHOULD_BE_NOT_NULL = "Password should be not null";

	// slot
	public static final String SLOT_ALREADY_CREATED = "slot already created";
	public static final String SLOT_AVAILABLE = "AVAILABLE";
	public static final String SLOT_NOT_AVAILABLE = "slot not available";
}
