package com.documentmgmt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.documentmgmt.model.DocumentMetadata;
import com.documentmgmt.service.IDocumentMgmtService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * REST Web Service for the File Management services
 * {@link IDocumentMgmtService}.
 * 
 * /filemgmt/content Create File POST file: A file posted in multipart request
 * metadata: metadat properties as String
 * 
 * 
 * /filemgmt/content/{fileId}/metadata Get Metadata GET fileId: unique file Id
 * which you got while creating the file content
 * 
 * 
 * /filemgmt/content/{fileId} Get content fileId: unique file Id which you got
 * while creating the file content
 * 
 * /filemgmt/content/documents?category={category}&status={status} find
 * Documents category : category of the document status: status of the document
 * 
 * @author vnondapaka
 *
 */
@RestController
@RequestMapping(value = "/filemgmt/content")
public class DocumentMgmtController {

	@Autowired
	IDocumentMgmtService documentMgmtService;

	/**
	 * Create content with metadata. Written this method to work for smaller file sizes.
	 * 
	 *TODO: Improve the method to handle for large file sizes using InputStream 
	 * Url: /filemgmt/content
	 * 
	 * @param file
	 *            A file posted in multipart request
	 * @param metadata
	 *            properties of file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public String createFile(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "metadata") String metadata) throws IOException {
		if (file == null || file.isEmpty() || metadata == null || metadata.isEmpty()) {
			throw new IllegalArgumentException("File or Metadata is not provided");
		}
		DocumentMetadata docMetadata = new ObjectMapper().readValue(metadata, DocumentMetadata.class);
		docMetadata.setFileName(file.getOriginalFilename());
		return documentMgmtService.createDocument(file.getBytes(), docMetadata);
	}

	/**
	 * Get Document Metadata
	 * 
	 * Url: /filemgmt/content/{fileId}/metadata
	 * 
	 * @param fileId
	 * @return Document Metadata
	 * @throws IOException
	 */
	@RequestMapping(value = "/{fileId}/metadata", method = RequestMethod.GET)
	public DocumentMetadata getMetadata(@PathVariable String fileId) throws IOException {
		return documentMgmtService.getDocumentMetadata(fileId);
	}

	/**
	 * Url: /filemgmt/content/{fileId}
	 * 
	 * @param fileId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/{fileId}", method = RequestMethod.GET)
	public HttpEntity<FileSystemResource> getDocument(@PathVariable String fileId) throws IOException {
		String filePath = documentMgmtService.getDocumentPath(fileId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// httpHeaders.setContentDispositionFormData("Content, filename);

		return new ResponseEntity<FileSystemResource>(new FileSystemResource(filePath), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Url: /filemgmt/content/documents?category={category}&status={status}
	 * 
	 * @param category
	 * @param status
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/documents", method = RequestMethod.GET)
	public HttpEntity<List<DocumentMetadata>> findDocument(@RequestParam(value = "category") String category,
			@RequestParam(value = "status") String status) throws IOException {
		if (StringUtils.isEmpty(category) || StringUtils.isEmpty(status)) {
			throw new IllegalArgumentException("Both category and status are required fields");
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<List<DocumentMetadata>>(documentMgmtService.findDocuments(category, status),
				httpHeaders, HttpStatus.OK);
	}

}
