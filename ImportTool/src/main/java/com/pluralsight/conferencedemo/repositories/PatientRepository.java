package com.pluralsight.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conferencedemo.models.batch.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Long> {   

 
}
