-- WIFI_INFO
CREATE TABLE `WIFI_INFO` (
	`xSwifiMgrNo`      VARCHAR(255) NOT NULL ,
	`xSwifiWrdofc`     VARCHAR(255) NULL     ,
	`xSwifiMainNm`     VARCHAR(255) NULL     ,
	`xSwifiAdres1`     VARCHAR(255) NULL     ,
	`xSwifiAdres2`     VARCHAR(255) NULL     ,
	`xSwifiInstlFloor` VARCHAR(255) NULL     ,
	`xSwifiInstlTy`    VARCHAR(255) NULL     ,
	`xSwifiInstlMby`   VARCHAR(255) NULL     ,
	`xSwifiSvcSe`      VARCHAR(20)  NULL     ,
	`xSwifiCmcwr`      VARCHAR(255) NULL     ,
	`xSwifiCnstcYear`  VARCHAR(255) NULL     ,
	`xSwifiInoutDoor`  VARCHAR(255) NULL     ,
	`xSwifiRemars3`    VARCHAR(255) NULL     ,
	`lat`              DOUBLE(8,6)  NULL     ,
	`lnt`              DOUBLE(8,5)  NULL     ,
	`work_dttm`        DATETIME     NULL     
	, PRIMARY KEY (xSwifiMgrNo)
);

SELECT * FROM WIFI_INFO;
SELECT * FROM LOC_INQ_H;
SELECT SQL FROM sqlite_master where name = 'WIFI_INFO';

-- LOC_INQ_H
CREATE TABLE `LOC_INQ_H` (
	`inqNo`   INTEGER     PRIMARY KEY autoincrement,
	`lat`     DOUBLE(8,6) NULL ,    
	`lnt`     DOUBLE(8,5) NULL ,    
	`inq_dtm` DATETIME    NULL     
);

