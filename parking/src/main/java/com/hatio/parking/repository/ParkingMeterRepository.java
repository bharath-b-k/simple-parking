package com.hatio.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hatio.parking.entity.ParkingPosition;

@Repository
public interface ParkingMeterRepository extends CrudRepository<ParkingPosition, String> {

	@Query("Select p From ParkingPosition p Where p.latitude Between :minLat And :maxLat And p.longitude Between :minLon And :maxLon "
			+ "and acos(sin(:lat)*sin(radians(p.latitude)) + cos(:lat)*cos(radians(p.latitude))*cos(radians(p.longitude) - :lon)) * 6357 < :rad")
	public List<ParkingPosition> findValidPositions(@Param("lat") double lat, @Param("lon") double lon,
			@Param("minLat") double minLat, @Param("maxLat") double maxLat, @Param("minLon") double minLon,
			@Param("maxLon") double maxLon, @Param("rad") double rad);
}
