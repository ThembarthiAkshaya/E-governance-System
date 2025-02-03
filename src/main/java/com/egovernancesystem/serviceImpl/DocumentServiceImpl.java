package com.egovernancesystem.serviceImpl;

import java.util.List;

import com.egovernancesystem.dao.DocumentDAO;
import com.egovernancesystem.entities.Document;
import com.egovernancesystem.service.DocumentService;

public class DocumentServiceImpl implements DocumentService {

    private DocumentDAO documentDao;

    // Constructor to initialize DAO
    public DocumentServiceImpl() {
        documentDao = new DocumentDAO();
    }

    @Override
    public int insertDocument(Document document) {
        return documentDao.insertDocument(document); // Call DAO to insert the document into the database
    }

    @Override
    public int updateDocument(Document document) {
        return documentDao.updateDocument(document); // Call DAO to update the document in the database
    }

    @Override
    public int deleteDocument(String documentId) {
        return documentDao.deleteDocument(documentId); // Call DAO to delete the document by documentId
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentDao.getAllDocuments(); // Call DAO to fetch all documents
    }

    @Override
    public Document getDocumentById(String documentId) {
        return documentDao.getDocumentById(documentId); // Call DAO to get document by documentId
    }
}
