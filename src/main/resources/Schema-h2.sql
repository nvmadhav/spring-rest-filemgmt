CREATE TABLE DOCUMENT (
	DOCUMENT_ID VARCHAR(500) NOT NULL PRIMARY KEY ,
	FILE_NAME VARCHAR(250) ,
	FILE_PATH VARCHAR(250)
) ;
CREATE TABLE DOCUMENT_METADATA  (
	DOCUMENT_ID VARCHAR(500) NOT NULL PRIMARY KEY ,
	FILE_NAME VARCHAR(100) NOT NULL ,
	CATEGORY VARCHAR(250), 
	STATUS VARCHAR(10),
	DOCTYPE VARCHAR(10),
	SOURCE VARCHAR(10),
	CREATE_DT TIMESTAMP DEFAULT NOW(),
	UPDATE_DT TIMESTAMP
) ;
