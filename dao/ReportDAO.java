package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Report;
import com.egovernancesystem.utils.HibernateUtils;

public class ReportDAO {

    /*---- Method to submit a report for a department into the database -----*/
    public int insertReport(Report report) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(report);  // Save the Report object
            transaction.commit();  // Commit the transaction
            row = 1;               // Indicating success
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;               // In case of failure
        } finally {
            session.close();       // Ensure session is closed
        }
        return row;
    }

    /*---- Method to update a report in the database -----*/
    public int updateReport(Report report) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Create an HQL query to update specific fields of the Report entity
            String hql = "UPDATE Report r SET r.reportType = :reportType, r.reportDate = :reportDate, r.department = :department WHERE r.reportId = :reportId";
            
            Query query = session.createQuery(hql);
            query.setParameter("reportType", report.getReportType());  // Set new report type
            query.setParameter("reportDate", report.getReportDate());  // Set new report date
            query.setParameter("department", report.getDepartment()); // Set new department
            query.setParameter("reportId", report.getReportId());     // Set report ID to identify the record
            
            row = query.executeUpdate();  // Execute the update query
            transaction.commit();         // Commit the transaction
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;                      // In case of failure
            transaction.rollback();       // Rollback transaction in case of error
        } finally {
            session.close();              // Ensure session is closed
        }
        return row;
    }

    /*---- Method to delete a report by reportId -----*/
    public int deleteReport(String reportId) {
        int result = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Fetch the report object first to ensure it exists
            Report report = session.get(Report.class, reportId);  // Use the reportId (String)

            if (report != null) {
                session.delete(report);  // Delete the Report object
                transaction.commit();    // Commit the transaction
                result = 1;              // Successful deletion
            } else {
                result = 0;  // Report not found
            }
        } catch (Exception e) {
            transaction.rollback();  // Rollback if there is an error
            e.printStackTrace();
        } finally {
            session.close();          // Ensure session is closed
        }
        return result;
    }

    /*---- Method to fetch all reports from the database -----*/
    public List<Report> getAllReports() {
        List<Report> reportList = null;
        try (Session session = HibernateUtils.getsFactory().openSession()) {
            // Create a query to fetch all Report records
            Query<Report> query = session.createQuery("from Report", Report.class);
            // Execute the query and get the result list
            reportList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportList;
    }

    
}

/*
 // Method to fetch a report by reportId 
    public Report getReportById(String reportId) {
        Report report = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Fetch the report object by reportId
            report = session.get(Report.class, reportId);  // reportId is of type String
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return report;  // Return null if not found
    }

    // Method to fetch reports by departmentId 
    public List<Report> getReportsByDepartmentId(String departmentId) {
        List<Report> reportList = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Create a query to fetch reports associated with a specific department
            String hql = "FROM Report r WHERE r.department.departmentId = :departmentId";
            Query query = session.createQuery(hql, Report.class);
            query.setParameter("departmentId", departmentId);  // Set department ID filter
            reportList = query.list();  // Execute the query and get the result list
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return reportList;
    }
    */
