package com.CabBookingSystem.Services;

import java.util.List;

import com.CabBookingSystem.models.Driver;
import com.CabBookingSystem.models.Ride;

public interface RideService {
	public List<Ride> find_ride(String username , double[] source , double[] destination);
	 public void chooseRide(String username, Driver driver);
}
