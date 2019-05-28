package com.farmacia.service;

import java.util.List;

import com.farmacia.model.Medication;

public interface MedicationService {
	
	void save (Medication medication);
	Medication findById(Long id);
	public List<Medication> findAll();
}
