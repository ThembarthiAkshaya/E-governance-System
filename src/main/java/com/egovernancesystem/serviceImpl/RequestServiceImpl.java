package com.egovernancesystem.serviceImpl;

import java.util.List;

import com.egovernancesystem.dao.RequestDAO;
import com.egovernancesystem.dao.TaxRecordDAO;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.entities.ServiceRequest;
import com.egovernancesystem.entities.TaxRecord;
import com.egovernancesystem.service.RequestService;

public class RequestServiceImpl implements RequestService {
	private RequestDAO requestDao;

    // Constructor to initialize DAO
    public RequestServiceImpl() {
    	requestDao = new RequestDAO();
    }

    /*--- Insert a new tax record ---*/
    @Override
    public int insertRequest(ServiceRequest servicerequest) {
        // Call DAO method to insert tax record and return result
        return requestDao.insertRequest(servicerequest);
    }
    
    @Override
    public int updateRequest(ServiceRequest servicerequest) {
        return requestDao.updateRequest(servicerequest); // Call DAO to update the service in the database
    }

    @Override
    public int deleteRequest(String requestId) {
        return requestDao.deleteRequest(requestId); // Call DAO to delete the service by serviceId
    }

    /*--- Get a tax record by its ID ---*/
    @Override
    public ServiceRequest getServiceRequestById(String taxId) {
        // Call DAO method to get tax record by ID
        return requestDao.getServiceRequestById(taxId);
    }

    /*--- Get tax records by citizen ID ---*/
    @Override
    public List<ServiceRequest> getServiceRequestByCitizen(String citizenId) {
        // Call DAO method to get tax records by citizen ID
        return requestDao.getServiceRequestByCitizen(citizenId);
    }
}
