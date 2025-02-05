package com.egovernancesystem.serviceImpl;

import java.util.List;

import com.egovernancesystem.dao.CitizenDAO;
import com.egovernancesystem.entities.Citizen;
import com.egovernancesystem.service.CitizenService;

public class CitizenServiceImpl implements CitizenService {

    private CitizenDAO citizenDao;

    // Constructor to initialize DAO
    public CitizenServiceImpl() {
        citizenDao = new CitizenDAO();
    }

    @Override
    public int insertCitizen(Citizen citizen) {
        return citizenDao.insertCitizen(citizen);
    }

    @Override
    public int updateCitizen(Citizen citizen) {
    	return citizenDao.updateCitizen(citizen);
    }

    @Override
    public int deleteCitizen(String citizenId) {
        return citizenDao.deleteCitizen(citizenId);  // Call DAO to delete the citizen by citizenId
    }
    @Override
    public List<Citizen> getAllCitizens() {
        return citizenDao.getAllCitizens();  // Fetch all citizens from DAO
    }
    @Override
    public Citizen getCitizenById(String citizenId) {
        return citizenDao.getCitizenById(citizenId);  // Call DAO to get the citizen details by citizenId
    }
}
