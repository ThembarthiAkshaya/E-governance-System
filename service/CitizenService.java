package com.egovernancesystem.service;

import java.util.List;

import com.egovernancesystem.entities.*;

public interface CitizenService {
	
	/*--- Method to register Student -----*/
	int insertCitizen(Citizen citizen);
	
	/*--- Method to update Student -----*/
	int updateCitizen(Citizen citizen);
	
	/*--- Method to delete citizen -----*/
	 int deleteCitizen(String citizenId);
	 
	 /*------ Method to get citizen by ID ----*/
	 Citizen getCitizenById(String citizenId); 
	 
	// Method to get all citizens
	    List<Citizen> getAllCitizens();
}
