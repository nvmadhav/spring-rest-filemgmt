package com.documentmgmt.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.documentmgmt.model.DocumentMetadata;
import com.documentmgmt.repository.IDocumentDao;

@Service("docMgmtService")
public class DocumentMgmtService implements IDocumentMgmtService{
	
	    @Autowired
	    private IDocumentDao documentDao;
	    
	    public List<DocumentMetadata> findDocuments(String category, String status) throws IOException {
	        return documentDao.findByCategoryAndStatus(category, status);
	    }
	    
	    public DocumentMetadata getDocumentMetadata(String fileId) {
	    	return documentDao.getDocumentMetadata(fileId);
	    }
	    
	    public String getDocumentPath(String id) throws IOException {
	        return documentDao.getDocumentPath(id);
	    }

		public String createDocument(byte[] fileData, DocumentMetadata metaData) {
	    	return documentDao.insert(fileData,  metaData);
		}
	}
