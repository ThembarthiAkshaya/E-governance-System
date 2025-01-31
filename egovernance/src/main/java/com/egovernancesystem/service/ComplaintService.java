package com.egovernancesystem.service;

import com.egovernancesystem.entities.Complaint;
import java.util.List;

public interface ComplaintService {

    /*--- Method to insert a new complaint ---*/
    int insertComplaint(Complaint complaint);
    
    /*--- Method to update an existing complaint ---*/
    int updateComplaint(Complaint complaint);
    
    /*--- Method to delete a complaint by its ID ---*/
    int deleteComplaint(String complaintId);
    
    /*--- Method to get a complaint by its ID ---*/
    Complaint getComplaintById(String complaintId);
    
    /*--- Method to get all complaints ---*/
    List<Complaint> getAllComplaints();
    
    /*--- Method to get complaints by citizen ---*/
    List<Complaint> getComplaintsByCitizen(String citizenId);
}
