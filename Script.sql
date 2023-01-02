-- WIFI_INFO
CREATE TABLE `WIFI_INFO` (
	`xSwifiMgrNo`      VARCHAR(50) NOT NULL ,
	`xSwifiWrdofc`     VARCHAR(50) NULL     ,
	`xSwifiMainNm`     VARCHAR(255) NULL     ,
	`xSwifiAdres1`     VARCHAR(255) NULL     ,
	`xSwifiAdres2`     VARCHAR(255) NULL     ,
	`xSwifiInstlFloor` VARCHAR(50) NULL     ,
	`xSwifiInstlTy`    VARCHAR(255) NULL     ,
	`xSwifiInstlMby`   VARCHAR(255) NULL     ,
	`xSwifiSvcSe`      VARCHAR(50)  NULL     ,
	`xSwifiCmcwr`      VARCHAR(50) NULL     ,
	`xSwifiCnstcYear`  VARCHAR(20) NULL     ,
	`xSwifiInoutDoor`  VARCHAR(20) NULL     ,
	`xSwifiRemars3`    VARCHAR(255) NULL     ,
	`lat`              REAL  NULL     ,
	`lnt`              REAL  NULL     ,
	`work_dttm`        DATETIME     NULL     
	, PRIMARY KEY (xSwifiMgrNo)
);

SELECT * FROM WIFI_INFO;
SELECT * FROM LOC_INQ_H;
SELECT SQL FROM sqlite_master where name = 'WIFI_INFO';

-- LOC_INQ_H
CREATE TABLE `LOC_INQ_H` (
	`inqNo`   INTEGER     PRIMARY KEY autoincrement,
	`lat`     REAL NULL ,    
	`lnt`     REAL NULL ,    
	`inq_dtm` DATETIME    NULL     
);


