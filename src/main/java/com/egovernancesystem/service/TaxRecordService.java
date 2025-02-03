package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Complaint;
import com.egovernancesystem.entities.TaxRecord;

public interface TaxRecordService
{
	/*--- Method to insert a new complaint ---*/
    int insertTax(TaxRecord tax);
    
    /*--- Method to get a tax by its ID ---*/
    TaxRecord getTaxRecordById(String taxId);
    
    /*--- Method to get complaints by citizen ---*/
    List<TaxRecord> getTaxRecordsByCitizen(String citizenId);
}
