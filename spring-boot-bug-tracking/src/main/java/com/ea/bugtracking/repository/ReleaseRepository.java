package com.ea.bugtracking.repository;

import org.springframework.data.repository.CrudRepository;

import com.ea.bugtracking.entity.Release;

public interface ReleaseRepository extends CrudRepository<Release, Long> {
}