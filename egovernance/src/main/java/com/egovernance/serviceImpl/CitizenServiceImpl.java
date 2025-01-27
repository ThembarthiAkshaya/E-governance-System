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
        // Delegate the insert operation to DAO (no need to pass the Citizen object)
        citizenDao.insertCitizenFromInput();
    }
    
    @Override
    public void updateCitizen(Citizen citizen) {
        // Delegate the update operation to DAO (no need to pass the Citizen object)
        citizenDao.updateCitizenFromInput();
    }
    
    @Override
    public void deleteCitizen(Citizen citizen) {
        // Delegate the delete operation to DAO (no need to pass the Citizen object)
        citizenDao.deleteCitizenFromInput();
    }
    
    @Override
    public void getAllCitizen(Citizen citizen) {
        // Delegate the delete operation to DAO (no need to pass the Citizen object)
        citizenDao.getAllCitizen();
    }
    
    @Override
    public void getCitizenById(Citizen citizen) {
        // Delegate the delete operation to DAO (no need to pass the Citizen object)
        citizenDao.getCitizenById();
    }
}
