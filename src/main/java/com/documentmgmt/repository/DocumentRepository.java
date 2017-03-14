package com.documentmgmt.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.documentmgmt.model.DocumentMetadata;

public interface DocumentRepository extends JpaRepository<DocumentMetadata,String> { 
	
	  @Query("SELECT t FROM DocumentMetadata t where t.category = :category AND t.status = :status")
	    public List<DocumentMetadata> findByCategoryAndStatus(@Param("category") String category, 
	                                                    @Param("status") String status);
	    
	  @Query("SELECT t FROM DocumentMetadata t where t.createDt > :createDt")
	  public List<DocumentMetadata> findDocumentsByDate(@Param("createDt") Date createDt);	  
}


