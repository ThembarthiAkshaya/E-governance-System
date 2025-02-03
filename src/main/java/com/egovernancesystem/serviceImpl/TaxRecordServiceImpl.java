package com.egovernancesystem.serviceImpl;

import java.util.List;
import com.egovernancesystem.dao.TaxRecordDAO;
import com.egovernancesystem.entities.TaxRecord;
import com.egovernancesystem.service.TaxRecordService;

public class TaxRecordServiceImpl implements TaxRecordService {

    private TaxRecordDAO taxRecordDao;

    // Constructor to initialize DAO
    public TaxRecordServiceImpl() {
        taxRecordDao = new TaxRecordDAO();
    }

    /*--- Insert a new tax record ---*/
    @Override
    public int insertTax(TaxRecord taxRecord) {
        // Call DAO method to insert tax record and return result
        return taxRecordDao.insertTax(taxRecord);
    }

    /*--- Get a tax record by its ID ---*/
    @Override
    public TaxRecord getTaxRecordById(String taxId) {
        // Call DAO method to get tax record by ID
        return taxRecordDao.getTaxRecordById(taxId);
    }

    /*--- Get tax records by citizen ID ---*/
    @Override
    public List<TaxRecord> getTaxRecordsByCitizen(String citizenId) {
        // Call DAO method to get tax records by citizen ID
        return taxRecordDao.getTaxRecordByCitizen(citizenId);
    }
}
