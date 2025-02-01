package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.*;

public interface ApplicationService
{

	int insertApplication(Application application);

	/*------ Method to get application by ID ----*/
	Application getApplicationById(String applicationId); 

	// Method to get all applications
	List<Application> getAllApplications();
}
