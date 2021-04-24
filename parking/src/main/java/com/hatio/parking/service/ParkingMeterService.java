package com.hatio.parking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hatio.parking.entity.ParkingPosition;
import com.hatio.parking.repository.ParkingMeterRepository;

@Service
public class ParkingMeterService {

	@Autowired
	private ParkingMeterRepository parkingMeterRepository;

	public void saveAll(List<ParkingPosition> parkingPositions) {
		this.parkingMeterRepository.saveAll(parkingPositions);
	}

	public List<ParkingPosition> getValidPakingPos(double lat, double lng, double radius) {
		double[] coord = new double[4];
		coord[0] /* minLatitude */ = lat - (radius * 2) / 60.0;
		coord[1] /* maxLatitude */ = lat + (radius * 2) / 60.0;
		coord[2] /* minLongitude */ = (lng * 2) / 60.0 * (Math.cos(coord[0]));
		coord[3] /* maxLongitude */ = (lng * 2) / 60.0 * (Math.cos(coord[1]));
		return this.parkingMeterRepository.findValidPositions(lat, lng, coord[0], coord[1], coord[2], coord[3], radius);
	}
}
