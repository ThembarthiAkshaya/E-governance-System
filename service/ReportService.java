package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Report;

public interface ReportService {

	/*--- Method to submit a report for a department ---*/
	int insertReport(Report report);

	/*--- Method to update a report ---*/
	int updateReport(Report report);

	/*--- Method to delete a report by report ID ---*/
	int deleteReport(String reportId);
	
	// Method to view a report's details by report ID 
	Report getReportById(String reportId);

	/*--- Method to view all reports ---*/
	List<Report> getAllReports();

}
/*

// Method to view all reports related to a specific department
List<Report> getReportsByDepartmentId(String departmentId);
 */