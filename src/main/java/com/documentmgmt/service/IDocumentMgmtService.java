package com.documentmgmt.service;

import java.io.IOException;
import java.util.List;

import com.documentmgmt.model.DocumentMetadata;


/**
 * This is service interface which provides the methods for document management.
 * @author vnondapaka
 *
 */
public interface IDocumentMgmtService {
    
    /**
     * This method is used to create the document in the file system and 
     * stores metadata in the database
     * @param fileData
     * @param metaData
     * @return file Id
     */
	String createDocument(byte[] fileData, DocumentMetadata metaData);
    
    /**
     * THis method is used to retrieve the document metadata from 
     * database for a given file Id
     * @param fileId
     * @return
     */
    public DocumentMetadata getDocumentMetadata(String fileId) ;
    
    
    /**
     * THis method is used to return the file Path for a given fileId
     * @param id
     * @return
     * @throws IOException
     */
    public String getDocumentPath(String id) throws IOException;
    
    /**
     * THis method is used to find all the documents for a given category and status 
     * and return their metadata
     * @param category
     * @param status
     * @return List<DocumentMetadata>
     * @throws IOException
     */
    public List<DocumentMetadata> findDocuments(String category, String status) throws IOException;
}
