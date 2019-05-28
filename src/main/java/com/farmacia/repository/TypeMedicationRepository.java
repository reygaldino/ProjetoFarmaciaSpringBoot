package com.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmacia.model.TypeMedication;

public interface TypeMedicationRepository extends JpaRepository<TypeMedication, Long>{
	
	public List<TypeMedication> findByName(String name);
	
	
}
