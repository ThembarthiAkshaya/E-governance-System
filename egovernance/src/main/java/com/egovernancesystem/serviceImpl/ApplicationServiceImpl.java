package com.egovernancesystem.serviceImpl;

import java.util.List;

import com.egovernancesystem.dao.ApplicationDAO;
import com.egovernancesystem.entities.Application;
import com.egovernancesystem.service.*;

public class ApplicationServiceImpl implements ApplicationService {
    private ApplicationDAO applicationDao;

    // Constructor to initialize DAO
    public ApplicationServiceImpl() {
        applicationDao = new ApplicationDAO();
    }

    @Override
    public int insertApplication(Application application) {
        // Calls the DAO to insert the application
        return applicationDao.insertApplication(application);
    }

    @Override
    public List<Application> getAllApplications() {
        // Correcting the method call to fetch applications
        return applicationDao.getAllApplication();
    }

    @Override
    public Application getApplicationById(String applicationId) {
        // Correcting the method call to fetch application by its ID
        return applicationDao.getApplicationById(applicationId);
    }
}

