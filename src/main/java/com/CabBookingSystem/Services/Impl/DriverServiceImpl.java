package com.CabBookingSystem.Services.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.CabBookingSystem.Exceptions.DriverRegistrationException;
import com.CabBookingSystem.Services.DriverService;
import com.CabBookingSystem.models.Driver;

@Service
public class DriverServiceImpl implements DriverService {
	
	public static Map<String , Driver> drivers = new HashMap<>();
	@Override
	public void add_driver(Driver driver) throws DriverRegistrationException {
		// Add driver
		if(isDriverInvalid(driver)) {
			throw new DriverRegistrationException("Invalid driver Exception");
		}
			drivers.put(driver.getName(), driver);
	}
	
//	Utility method to check if any required field is null or empty
	private boolean isDriverInvalid(Driver driver) {
		return driver == null ||
				driver.getName() == null || driver.getName().isEmpty() ||
				driver.getGender() == null || driver.getGender().isEmpty() ||
				driver.getVehicleDetails() == null || driver.getVehicleDetails().isEmpty() ||
				driver.getCurrentLoaction() == null || driver.getCurrentLoaction().length == 0 ;
	}

}
