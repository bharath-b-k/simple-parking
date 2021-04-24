package com.hatio.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hatio.parking.entity.ParkingPosition;
import com.hatio.parking.service.ParkingMeterService;

@RestController
@RequestMapping("/parking")
public class ParkingMeterController {

	@Autowired
	private ParkingMeterService parkingMeterService;

	@GetMapping("/positions/{latitude}/{longitude}/{radius}")
	public List<ParkingPosition> getParkingPositions(@PathVariable double latitude, @PathVariable double longitude,
			@PathVariable double radius) {
		return this.parkingMeterService.getValidPakingPos(latitude, longitude, radius);
	}

}
