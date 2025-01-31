package com.egovernancesystem.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.egovernancesystem.entities.Document;
import com.egovernancesystem.utils.HibernateUtils;

public class DocumentDAO {

    /*---- Method to insert document into the database -----*/
    public int insertDocument(Document document) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(document);  // Save the Document object
            transaction.commit();     // Commit the transaction
            row = 1;                  // Indicating success
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;                  // In case of failure
        } finally {
            session.close();          // Ensure session is closed
        }
        return row;
    }

    /*---- Method to update document in the database -----*/
    public int updateDocument(Document document) {
        int row = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Use merge() instead of update() to ensure the entity is attached to the session
            session.merge(document);  // Merge the Document object (works with detached entities)
            transaction.commit();     // Commit the transaction
            row = 1;                  // Indicating success
        } catch (Exception e) {
            e.printStackTrace();
            row = 0;                  // In case of failure
            transaction.rollback();   // Rollback in case of error
        } finally {
            session.close();          // Ensure session is closed
        }
        return row;
    }

    /*---- Method to delete a document by documentId -----*/
    public int deleteDocument(String documentId) {
        int result = 0;
        Session session = HibernateUtils.getsFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Fetch the document object first to ensure it exists
            Document document = session.get(Document.class, documentId);  // Use the documentId (String)

            if (document != null) {
                session.delete(document);  // Delete the Document object
                transaction.commit();      // Commit the transaction
                result = 1;                // Successful deletion
            } else {
                result = 0;  // Document not found
            }
        } catch (Exception e) {
            transaction.rollback();  // Rollback if there is an error
            e.printStackTrace();
        } finally {
            session.close();          // Ensure session is closed
        }
        return result;
    }

    /*---- Method to fetch all documents from the database -----*/
    public List<Document> getAllDocuments() {
        List<Document> documentList = null;
        try (Session session = HibernateUtils.getsFactory().openSession()) {
            // Create a query to fetch all Document records
            Query<Document> query = session.createQuery("from Document", Document.class);
            // Execute the query and get the result list
            documentList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentList;
    }

    /*---- Method to fetch a document by documentId -----*/
    public Document getDocumentById(String documentId) {
        Document document = null;
        Session session = HibernateUtils.getsFactory().openSession();
        try {
            // Fetch the document object by documentId
            document = session.get(Document.class, documentId);  // documentId is of type String
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();  // Ensure session is closed
        }
        return document;  // Return null if not found
    }
}
