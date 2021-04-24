package com.hatio.parking;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hatio.parking.entity.ParkingPosition;
import com.hatio.parking.service.ParkingMeterService;

@SpringBootApplication
public class ParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ParkingMeterService service) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<ParkingPosition>> typeReference = new TypeReference<List<ParkingPosition>>() {
			};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/parking-meters.json");
			try {
				List<ParkingPosition> parkingPositions = mapper.readValue(inputStream, typeReference);
				service.saveAll(parkingPositions);
				System.out.println("Positions Saved!!!");
			} catch (IOException e) {
				System.out.println("Unable to save the positions " + e.getMessage());
			}
		};
	}
}
