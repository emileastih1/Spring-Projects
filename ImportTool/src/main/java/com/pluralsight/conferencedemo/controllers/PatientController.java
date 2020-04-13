package com.pluralsight.conferencedemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluralsight.conferencedemo.models.batch.PatientEntity;
import com.pluralsight.conferencedemo.repositories.PatientRepository;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

	@Autowired
	PatientRepository patientRepository;
	
	@GetMapping
	public List<PatientEntity> getAllPatients(){
		return patientRepository.findAll();
	}
	
	
	@GetMapping
	@RequestMapping(value = "{id}")
	public PatientEntity getPatient(@PathVariable Long id) {
		return patientRepository.findById(id).get();
	}
	

}
