package com.egovernance.service;

import com.egovernance.entities.*;

public interface CitizenService {
	
	/*--- Method to register Student -----*/
	void insertCitizen(Citizen citizen);
	void updateCitizen(Citizen citizen);
	void deleteCitizen(Citizen citizen);
	void getAllCitizen(Citizen citizen);
	void getCitizenById(Citizen citizen);
	/*
    void updateCitizen(SessionFactory sf);
    void deleteCitizen(SessionFactory sf);
    void getAllCitizens(SessionFactory sf);
    void getCitizen(SessionFactory sf);
    */
}
