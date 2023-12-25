package com.utils.app.trucking.domain.driving;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingRepository extends JpaRepository<Driving, String> {
}
