package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.ServiceDAO;
import com.egovernancesystem.entities.Service;
import com.egovernancesystem.service.ServiceService;

public class ServiceServiceImpl implements ServiceService {
    private ServiceDAO serviceDao;

    // Constructor to initialize DAO
    public ServiceServiceImpl() {
        serviceDao = new ServiceDAO();  // Initialize the DAO for interacting with the database
    }

    @Override
    public int insertService(Service service) {
        return serviceDao.insertService(service); // Call DAO to insert the service into the database
    }

    @Override
    public int updateService(Service service) {
        return serviceDao.updateService(service); // Call DAO to update the service in the database
    }

    @Override
    public int deleteService(String serviceId) {
        return serviceDao.deleteService(serviceId); // Call DAO to delete the service by serviceId
    }

    @Override
    public Service getServiceById(String serviceId) {
        return serviceDao.getServiceById(serviceId); // Call DAO to get service by serviceId
    }

    @Override
    public List<Service> getAllServices() {
        return serviceDao.getAllServices(); // Call DAO to fetch all services
    }
}
