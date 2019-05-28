package com.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.model.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

	
}
