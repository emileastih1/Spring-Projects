package com.pluralsight.conferencedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pluralsight.conferencedemo.models.Speaker;

public interface SpeakerRepository extends JpaRepository<Speaker, Long>{

}
