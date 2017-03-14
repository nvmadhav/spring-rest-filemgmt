package com.documentmgmt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_METADATA")
public class DocumentMetadata implements Serializable {

	private static final long serialVersionUID = 7854843308949406575L;

	@Id
	@Column(name = "DOCUMENT_ID")
	private String documentId;

	@Column(name = "FILE_NAME")
	private String fileName;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "DOCTYPE")
	private String docType;

	@Column(name = "SOURCE")
	private String source;

	@Column(name = "CREATE_DT")
	private Date createDt;

	@Column(name = "UPDATE_DT")
	private Date updateDt;

	public DocumentMetadata() {

	}

	public String getDocumentId() {
		return documentId;
	}

	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}