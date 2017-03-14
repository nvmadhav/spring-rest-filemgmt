package com.documentmgmt.repository;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentmgmt.model.DocumentMetadata;

/**
 * Data access object to insert, find and load {@link DocumentMetadata}s.
 * 
 * DocumentDao saves documents in the file system. Stors the metadata in the database.
 * For each document a folder is created. The folder contains the document
 * The name of the documents folder is the UUID of the document.
 * 
 * 
 * @author vnondapaka
 *
 */
@Service("documentDao")
public class DocumentDao implements IDocumentDao {

    private static final Logger LOG = Logger.getLogger(DocumentDao.class);
    
    public static final String DIRECTORY = "documents";
    public static final String META_DATA_FILE_NAME = "metadata.properties";
    
    @Autowired
    private DocumentRepository documentRepository;
    
    @PostConstruct
    public void init() {
        createDirectory(DIRECTORY);
    }
    
    /* Creates a document in the folder with the UUID of the document. 
     * It stores the metdata in to the database.
     * @see com.documentmgmt.repository.IDocumentDao#insert(byte[], com.documentmgmt.model.DocumentMetadata)
     */
    public String insert(byte[] fileData,DocumentMetadata metaData) {
    	String uuid = UUID.randomUUID().toString();
    	metaData.setDocumentId(uuid);
    	metaData.setCreateDt(Calendar.getInstance().getTime());
    	try {
            createDirectory(uuid);
            saveFileData(uuid, fileData, metaData.getFileName());
            saveMetaData(metaData);
        } catch (IOException e) {
            String message = "Error while inserting document";
            LOG.error(message, e);
            throw new RuntimeException(message, e);
        }
        return uuid;
    }
    
    
    /* This method is used to retrieve the docment metadata from the database based on fileId
     * @see com.documentmgmt.repository.IDocumentDao#getDocumentMetadata(java.lang.String)
     */
    public DocumentMetadata getDocumentMetadata(String fileId) {
    	DocumentMetadata docMetadata = null;
        try {
        	docMetadata = documentRepository.findOne(fileId);
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching document metadata", e);
        }
        return docMetadata;
    }
    
    public List<DocumentMetadata> findByCategoryAndStatus(String category, String status) throws IOException  {
        return documentRepository.findByCategoryAndStatus(category,status);
    }

    public String getDocumentPath(String uuid) throws IOException {
        DocumentMetadata metadata = getDocumentMetadata(uuid);
        if(metadata==null) {
            return null;
        }
        return getFilePath(metadata);
     }

    private String getFilePath(DocumentMetadata metadata) {
        String dirPath = getDirectoryPath(metadata.getDocumentId());
        StringBuilder sb = new StringBuilder();
        sb.append(dirPath).append(File.separator).append(metadata.getFileName());
        return sb.toString();
    }
    
    private void saveFileData(String uuid, byte[] fileData,String fileName) throws IOException {
        String path = getDirectoryPath(uuid);
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(new File(path), fileName)));
        stream.write(fileData);
        stream.close();
    }
    
    public void saveMetaData(DocumentMetadata metadata) throws IOException {
    	documentRepository.save(metadata);
    }
    
    
    private String createDirectory(String uuid) {
        String path = getDirectoryPath(uuid);
        File file = new File(path);
        file.mkdirs();
        return path;
    }

    
    private String getDirectoryPath(String uuid) {
        StringBuilder sb = new StringBuilder();
        sb.append(DIRECTORY).append(File.separator).append(uuid);
        String path = sb.toString();
        return path;
    }

  
}
