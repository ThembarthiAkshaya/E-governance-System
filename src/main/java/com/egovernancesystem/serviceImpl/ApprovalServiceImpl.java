package com.egovernancesystem.serviceImpl;

import java.util.List;
import java.util.Set;
import com.egovernancesystem.dao.ApprovalDAO;
import com.egovernancesystem.entities.Approval;
import com.egovernancesystem.entities.Employee;
import com.egovernancesystem.entities.Payment;
import com.egovernancesystem.service.ApprovalService;

public class ApprovalServiceImpl implements ApprovalService {

    private ApprovalDAO approvalDao;

    // Constructor to initialize DAO
    public ApprovalServiceImpl() {
        approvalDao = new ApprovalDAO(); // Initialize the DAO class for database interaction
    }

    @Override
    public int insertApproval(Approval approval) {
        return approvalDao.insertApproval(approval);  // Call DAO to insert payment
    }

    @Override
    public Approval getApprovalById(String approvalId) {
        return approvalDao.getApprovalById(approvalId); // Fetch appointment details by ID
    }
    
    @Override
    public List<Approval> getAllApprovalsByEmployee(String employeeId) {
        return approvalDao.findApprovalsByEmployeeId(employeeId);  // Fetch approvals from DAO
    }

    @Override
    public int deleteApprovalsByEmployeeId(String employeeId) {
        // Delete all approvals related to the specific employee
        return approvalDao.deleteApprovalsByEmployeeId(employeeId);
    }

    @Override
    public int changeApprovalStatusByEmployeeId(String employeeId, String newStatus) {
        int result = approvalDao.changeApprovalStatusByEmployeeId(employeeId, newStatus);
        return result;
    }
}