package com.farmacia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmacia.model.Medication;
import com.farmacia.repository.MedicationRepository;

@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private MedicationRepository medicationRepository;
	
	@Override
	public void save(Medication medication) {
		medicationRepository.save(medication);
	}

	@Override
	public Medication findById(Long id) {
		
		return medicationRepository.getOne(id);
	}

	@Override
	public List<Medication> findAll() {
		return medicationRepository.findAll();
	}

}
