package com.documentmgmt;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.documentmgmt.model.DocumentMetadata;
import com.documentmgmt.repository.DocumentRepository;

@Component

/**
 * Scheduler which invokes every 5 seconds.  I scheduled this for every 5 seconds for 
 * testing purpose but we can schedule this for every hour
 * @author vnondapaka
 *
 */
public class DocumentScheduler {

	    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    @Autowired
	    private DocumentRepository documentRepository;

	    
	    /**
	     * This method runs for every 5 seconds and queries the database and prints the documents added in the last hour.
	     * TODO: add logger instead of System.out 
	     */
	    @Scheduled(fixedRate = 5000)
	    public void reportDocuments() {
	    	List<DocumentMetadata> documentsMetadata = documentRepository.findDocumentsByDate(new Date (System.currentTimeMillis()-TimeUnit.HOURS.toMillis (1)));
	    	System.out.println("documentMetdata>>"+documentsMetadata);
	    }
}
