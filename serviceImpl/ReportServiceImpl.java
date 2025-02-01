package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.ReportDAO;
import com.egovernancesystem.entities.Report;
import com.egovernancesystem.service.ReportService;

public class ReportServiceImpl implements ReportService {

	private ReportDAO reportDao;

	// Constructor to initialize DAO
	public ReportServiceImpl() {
		reportDao = new ReportDAO();  // Initialize the DAO for interacting with the database
	}

	@Override
	public int insertReport(Report report) {
		return reportDao.insertReport(report); // Call DAO to insert the report into the database
	}

	@Override
	public int updateReport(Report report) {
		return reportDao.updateReport(report); // Call DAO to update the report in the database
	}

	@Override
	public int deleteReport(String reportId) {
		return reportDao.deleteReport(reportId); // Call DAO to delete the report by reportId
	}

	@Override
	public List<Report> getAllReports() {
		return reportDao.getAllReports(); // Call DAO to fetch all reports
	}
}
/*
 @Override
    public Report getReportById(String reportId) {
        return reportDao.getReportById(reportId); // Call DAO to get report by reportId
    }
    @Override
    public List<Report> getReportsByDepartmentId(String departmentId) {
        return reportDao.getReportsByDepartmentId(departmentId); // Call DAO to fetch reports by departmentId
    }
 */
