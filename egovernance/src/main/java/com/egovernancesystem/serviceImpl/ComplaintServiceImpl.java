package com.egovernancesystem.serviceImpl;

import com.egovernancesystem.dao.CitizenDAO;
import com.egovernancesystem.dao.ComplaintDAO;
import com.egovernancesystem.entities.Complaint;
import com.egovernancesystem.service.ComplaintService;
import java.util.List;

public class ComplaintServiceImpl implements ComplaintService {
	 private ComplaintDAO complaintDao;

	    // Constructor to initialize DAO
	    public ComplaintServiceImpl() {
	    	complaintDao = new ComplaintDAO();
	    }
    
    /*--- Insert a new complaint ---*/
    @Override
    public int insertComplaint(Complaint complaint) {
        // Call DAO method to insert complaint and return result
        return complaintDao.insertComplaint(complaint);
    }

    /*--- Update an existing complaint ---*/
    @Override
    public int updateComplaint(Complaint complaint) {
        // Call DAO method to update complaint and return result
        return complaintDao.updateComplaint(complaint);
    }

    /*--- Delete a complaint by its ID ---*/
    @Override
    public int deleteComplaint(String complaintId) {
        // Call DAO method to delete complaint and return result
        return complaintDao.deleteComplaint(complaintId);
    }

    /*--- Get a complaint by its ID ---*/
    @Override
    public Complaint getComplaintById(String complaintId) {
        // Call DAO method to get complaint by ID
        return complaintDao.getComplaintById(complaintId);
    }

    /*--- Get all complaints ---*/
    @Override
    public List<Complaint> getAllComplaints() {
        // Call DAO method to get all complaints
        return complaintDao.getAllComplaints();
    }

    /*--- Get complaints by citizen ID ---*/
    @Override
    public List<Complaint> getComplaintsByCitizen(String citizenId) {
        // Call DAO method to get complaints by citizen
        return complaintDao.getComplaintsByCitizen(citizenId);
    }
}

