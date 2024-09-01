package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Login;

import com.example.demo.entity.WaterIntake;
@Repository
public interface WaterIntakeRepository extends JpaRepository<WaterIntake, Long> {
	 List<WaterIntake> findByUserAndDateBetween(Login user, LocalDate startDate, LocalDate endDate);

	WaterIntake findByUserAndDate(Login user, LocalDate date); 
	Optional<WaterIntake> findByUser(Login user);

	Collection<WaterIntake> findByUserAndDateOrderByDateDesc(Login user, LocalDate date);
	

	

	 
	 
	
}