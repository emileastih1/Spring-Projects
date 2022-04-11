package com.ea.bugtracking.service;

import com.ea.bugtracking.entity.Application;
import com.ea.bugtracking.exception.ApplicationNotFoundException;
import com.ea.bugtracking.repository.ApplicationRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public Iterable<Application> listApplications() {
        return applicationRepository.findAll();
    }
    
    @Override
    public Application findApplication(int id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);

        if(optionalApplication.isPresent())
            return optionalApplication.get();
        else
            throw new ApplicationNotFoundException("Application Not Found");
    }

}
