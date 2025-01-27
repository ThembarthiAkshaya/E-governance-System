package com.egovernance.serviceImpl;

import com.egovernance.dao.CitizenDAO;
import com.egovernance.entities.Citizen;
import com.egovernance.service.CitizenService;

public class CitizenServiceImpl implements CitizenService {

    private CitizenDAO citizenDao;

    // Constructor to properly initialize citizenDao
    public CitizenServiceImpl(CitizenDAO citizenDao) {
        this.citizenDao = citizenDao;  // Assign the passed DAO to the class field
    }

    @Override
    public void insertCitizen(Citizen citizen) {
        // Delegate the insert operation to DAO
        citizenDao.insertCitizen(citizen);
    }
    
    @Override
    public void updateCitizen(Citizen citizen) {
        // Delegate the insert operation to DAO
        citizenDao.updateCitizen(citizen);
    }
}

