package org.offshore.groundsampling.repository;


import org.offshore.groundsampling.model.Location;
import org.offshore.groundsampling.model.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample, Long> {
    List<Sample> findByLocation(Location location);
}

