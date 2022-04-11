package com.ea.bugtracking.service;

import com.ea.bugtracking.entity.Application;

public interface ApplicationService {
    Iterable<Application> listApplications();
    Application findApplication(int id);
}


