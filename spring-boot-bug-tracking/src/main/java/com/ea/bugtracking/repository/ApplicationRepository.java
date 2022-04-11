package com.ea.bugtracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.ea.bugtracking.entity.Application;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
