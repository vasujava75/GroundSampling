package org.offshore.groundsampling.repository;


import org.offshore.groundsampling.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
