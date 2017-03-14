package com.documentmgmt.repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.documentmgmt.model.DocumentMetadata;

/**
 * This interface provides the methods to create the documents on file system
 * and stores the data in the database.
 * @author vnondapaka
 *
 */
public interface IDocumentDao {

    
	/**
	 * This method is used to store the file on the file system 
	 * and its metadata in the database
	 * @param fileData
	 * @param metaData
	 * @return file ID (UUID)
	 */
	public String insert(byte[] fileData,DocumentMetadata metaData);

    
	/**
	 * This method is used to return the document metadata based on the given 
	 * file id
	 * @param fileId
	 * @return DocumentMetadata
	 */
    public DocumentMetadata getDocumentMetadata(String fileId);
    
    
    /**
     * This method is used to return the document path for a given fileId
     * @param uuid
     * @return String Path
     * @throws IOException
     */
    public String getDocumentPath(String uuid) throws IOException;
    
    /**
     * This method is used to return the list of DocumentMetadata which matches the 
     * given category and status values
     * @param category
     * @param status
     * @return List<DocumentMetadata>
     * @throws IOException
     */
    public List<DocumentMetadata> findByCategoryAndStatus(String category, String status) throws IOException;
    
}