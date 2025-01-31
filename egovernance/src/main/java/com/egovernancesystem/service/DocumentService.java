package com.egovernancesystem.service;

import java.util.List;
import com.egovernancesystem.entities.Document;

public interface DocumentService {

    /*--- Method to register Document -----*/
    int insertDocument(Document document);
    
    /*--- Method to update Document -----*/
    int updateDocument(Document document);
    
    /*--- Method to delete Document -----*/
    int deleteDocument(String documentId);
    
    /*--- Method to get Document by ID ----*/
    Document getDocumentById(String documentId);
    
    /*--- Method to get all Documents -----*/
    List<Document> getAllDocuments();
}

