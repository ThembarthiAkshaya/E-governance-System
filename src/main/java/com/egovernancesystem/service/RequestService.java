package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.ServiceRequest;

public interface RequestService
{
	/*--- Method to insert a new complaint ---*/
    int insertRequest(ServiceRequest request);
    
    /*--- Method to get a tax by its ID ---*/
    ServiceRequest getServiceRequestById(String taxId);
    
    /*--- Method to get complaints by citizen ---*/
    List<ServiceRequest> getServiceRequestByCitizen(String citizenId);
}
