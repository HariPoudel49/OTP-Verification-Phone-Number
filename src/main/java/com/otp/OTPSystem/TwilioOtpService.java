package com.otp.OTPSystem;


import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.otp.config.TwilioConfig;
import com.otp.dto.OtpRequest;
import com.otp.dto.OtpResponseDto;
import com.otp.enums.OtpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TwilioOtpService{
	
	@Autowired
	private TwilioConfig twilioConfig;
	Map<String, String> otpMap = new HashMap<>();
	
	
	public OtpResponseDto sendSMS(OtpRequest otpRequest) {
		OtpResponseDto otpResponseDto = null;
		try {
			PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());//to
			PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber()); // from
			String otp = generateOTP();
			String otpMessage = "Dear Customer , Your OTP is  " + otp + " for sending sms through Spring boot application. Thank You.";
			Message message = Message
					.creator(to, from,
							otpMessage)
					.create();
			otpMap.put(otpRequest.getUsername(), otp);
			otpResponseDto = new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
		} catch (Exception e) {
			e.printStackTrace();
			otpResponseDto = new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
		}
		return otpResponseDto;
	}
	
	public String validateOtp(OtpValidationRequest otpValidationRequest) {
		Set<String> keys = otpMap.keySet();
		String username = null;
		for(String key : keys)
			username = key;
		if (otpValidationRequest.getUsername().equals(username)) {
			otpMap.remove(username,otpValidationRequest.getOtpNumber());
			return "OTP is valid!";
		} else {
			return "OTP is invalid!";
		}
	}
	
	private String generateOTP() {
		return new DecimalFormat("000000")
				.format(new Random().nextInt(999999));
	}
	
}
