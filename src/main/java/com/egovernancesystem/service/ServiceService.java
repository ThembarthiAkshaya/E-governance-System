package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Service;

public interface ServiceService {
	/*--- Method to submit a report for a department ---*/
	int insertService(Service service);

	/*--- Method to update a report ---*/
	int updateService(Service service);

	/*--- Method to delete a report by report ID ---*/
	int deleteService(String serviceId);
	
	// Method to view a report's details by report ID 
	Service getServiceById(String serviceId);

	/*--- Method to view all reports ---*/
	List<Service> getAllServices();
}
