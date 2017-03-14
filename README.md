# spring-rest-filemgmt

This project contains document management services. These services stores the files on the file system and metadata in in-memory database. 


# Rest Services!

  - Upload File Along with Metadata
     -  Url: http://localhost:8080/filemgmt/content
     -  Method: PUT
     - Headers: Accept : multipart/form-data
     - Body: metadata : { 	"category": "test", 	"status": "NA", "docType":"petition","source":"TMNG" }
     - file:  (Select file)
     - Response: file UUID      
  - API to get file metadata
    -  Url: http://localhost:8080/filemgmt/content/{fileId}/metadata
    - Method: GET
  - API to download content stream
    -  Url:http://localhost:8080/filemgmt/content/{fileId}
    -  Method: GET    -  
  - API to search for file Ids with a search criterion
    -  Url: http://localhost:8080/filemgmt/content/documents?category=test&status=NA
    -  Method: GET
  - Scheduler to poll for new items
    - Refer DocumentScheduler
  - Drag and drop images (requires your Dropbox account be linked)


You can also:
  - View the h2 database using below URL
    - http://localhost:8080/h2

### Run
mvn:spring-boot:run
