drop table BS_AEConclusion;

drop table BS_AEInspection;

drop table BS_AEPeople;

drop table BS_AERectification;

drop table BS_AdmEnfcAnly;

drop table BS_AdmEnforce;

drop table BS_AdmPunish;

drop table BS_AdmPunishCons;

drop table BS_BusiEval;

drop table BS_Training;

drop table BS_WorkCheck;


create table BS_Dictionary
(
   ID                 VARCHAR(50)            not null,
   TYPE               VARCHAR(50),
   DKEY              VARCHAR(100),
   DVALUE            VARCHAR(500),
   DSumry            VARCHAR(1000),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
    Isenable               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AEConclusion
--==============================================================
create table BS_AEConclusion
(
   ID                   VARCHAR(50)            not null,
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(100),
   AEedOrgNo          VARCHAR(50),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   AEOpnionNo         VARCHAR(50),
   AEOpnionBook       VARCHAR(50),
   RelPeoples         VARCHAR(1000),
   RelORGNM           VARCHAR(1000),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AEInspection
--==============================================================
create table BS_AEInspection
(
   ID                   VARCHAR(50)            not null,
   ino                VARCHAR(50),
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(100),
   AEedOrgNo          VARCHAR(50),
   AERecordNo         VARCHAR(50),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   AEPrjNm            VARCHAR(100),
   AEInDate           TIMESTAMP,
   AEOutDate          TIMESTAMP,
   AEPeoples          VARCHAR(100),
   AEBasis            VARCHAR(50),
   EnquireRcd         VARCHAR(50),
   ActualrcdNO         VARCHAR(50),
   Actualrcd         VARCHAR(50),
   InRcd           VARCHAR(50),
   InRcdNO          VARCHAR(50),
   OutRcd           VARCHAR(50),
   OutRcdNO           VARCHAR(50),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AEPeople
--==============================================================
create table BS_AEPeople
(
   ID                   VARCHAR(50)            not null,
   PepName               VARCHAR(50),
   CertTp             VARCHAR(50),
   CertNo             VARCHAR(50),
   BankNo             VARCHAR(50),
   CreditNo           VARCHAR(50),
   OrgNo              VARCHAR(50),
   OrgNm              VARCHAR(100),
   DptNm              VARCHAR(100),
   Prcipal            VARCHAR(100),
   Tele               VARCHAR(50),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          VARCHAR(50),
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AERectification
--==============================================================
create table BS_AERectification
(
   ID                   VARCHAR(50)            not null,
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(100),
   AEedOrgNo          VARCHAR(50),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   AEOpnionNo         VARCHAR(50),
   TrackNo            VARCHAR(50),
   TrackContend       VARCHAR(500),
   TrackAtta          VARCHAR(50),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AdmEnfcAnly
--==============================================================
create table BS_AdmEnfcAnly
(
   ID                   VARCHAR(50)            not null,
   RegNo              VARCHAR(50),
   ChkOrgNm           VARCHAR(100),
   ChkOrgNo           VARCHAR(50),
   ChkedOrgNm         VARCHAR(100),
   ChkedOrgNo         VARCHAR(50),
   ChkWay             VARCHAR(100),
   ChkPepNum          BIGINT,
   ChkDayNum          BIGINT,
   FilePep            BIGINT,
   FileEnt            BIGINT,
   FileNum            BIGINT,
   EnqNum             BIGINT,
   Puned              DECIMAL(10,2),
   Puning             DECIMAL(10,2),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AdmEnforce
--==============================================================
create table BS_AdmEnforce
(
   ID                   VARCHAR(50)            not null,
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(1000),
   AEedOrgNo          VARCHAR(1000),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   PrjNm              VARCHAR(200),
   PrjBasis           VARCHAR(500),
   AEBasis            VARCHAR(500),
   AEContent          VARCHAR(500),
   AELimt             VARCHAR(100),
   AEWay              VARCHAR(50),
   AEPeople           VARCHAR(100),
   AEPlan             VARCHAR(500),
   DptOpnion          VARCHAR(500),
   DptPeople          VARCHAR(50),
   DptPeopleOrg       VARCHAR(100),
   DptPeopleOrgNo     VARCHAR(50),
   ChairOpnion        VARCHAR(500),
   ChairPeople        VARCHAR(50),
   ChairPeopleOrg     VARCHAR(100),
   ChairPeopleOrgNo   VARCHAR(50),
   AEStat             VARCHAR(20),
   RegPep             VARCHAR(20),
   RegOrgNm           VARCHAR(100),
   RegOrgNo           VARCHAR(50),
   RegDt              TIMESTAMP,
   Stat               VARCHAR(50),
   maxino              int,
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AdmPunish
--==============================================================
create table BS_AdmPunish
(
   ID                   VARCHAR(50)            not null,
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(100),
   AEedOrgNo          VARCHAR(50),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   AEOpnionNo         VARCHAR(50),
   PunishNo           VARCHAR(50),
   IllegalUnit        VARCHAR(200),
   IllegalPeople      VARCHAR(500),
   Reason             VARCHAR(500),
   Summarys            VARCHAR(500),
   SumryAtta          VARCHAR(50),
   DptOpnion          VARCHAR(500),
   PeopleOpnion       VARCHAR(500),
   ChairOpnion        VARCHAR(500),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AdmPunishCons
--==============================================================
create table BS_AdmPunishCons
(
   ID                   VARCHAR(50)            not null,
   AENo               VARCHAR(50),
   AEOrgNm            VARCHAR(100),
   AEOrgNo            VARCHAR(50),
   AEedOrgNm          VARCHAR(100),
   AEedOrgNo          VARCHAR(50),
   AEPlanStDate       TIMESTAMP,
   AEPlanTmDate       TIMESTAMP,
   AEOpnionNo         VARCHAR(50),
   PunishNo           VARCHAR(50),
   PunishBookNo       VARCHAR(50),
   EnforceUnit        VARCHAR(100),
   EnforceUnitNo      VARCHAR(50),
   PunishUnit         VARCHAR(100),
   PunishUnitNo       VARCHAR(50),
   PunishBookAtta     VARCHAR(50),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_BusiEval
--==============================================================
create table BS_BusiEval
(
   ID                   VARCHAR(50)            not null,
   BusiEvalNo         VARCHAR(50),
   OrgNm              VARCHAR(100),
   OrgNo              VARCHAR(50),
   EvalOrgNm          VARCHAR(100),
   EvalOrgNo          VARCHAR(50),
   A1                   VARCHAR(500),
   A2                   VARCHAR(500),
   A3                   VARCHAR(500),
   A4                   VARCHAR(500),
   A5                   VARCHAR(500),
   A6                   VARCHAR(500),
   A7                   VARCHAR(500),
   A8                   VARCHAR(500),
   A9                   VARCHAR(500),
   A10                  VARCHAR(500),
   A11                  VARCHAR(500),
   RegDt              VARCHAR(50),
   HistRec            VARCHAR(500),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_Training
--==============================================================
create table BS_Training
(
   ID                   VARCHAR(50)            not null,
   TrainingNo         VARCHAR(50),
   TranOrgNm          VARCHAR(100),
   TranOrgNo          VARCHAR(50),
   OperDpt            VARCHAR(100),
   Tele               VARCHAR(50),
   TranDt             VARCHAR(50),
   TranTerm           VARCHAR(200),
   TranCont           VARCHAR(500),
   LastDt             VARCHAR(50),
   TranTm             VARCHAR(50),
   TranPepNum         VARCHAR(50),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          VARCHAR(50),
   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_WorkCheck
--==============================================================
create table BS_WorkCheck
(
   ID                   VARCHAR(50)            not null,
   WorkChkNo          VARCHAR(50)            not null,
   Peps               VARCHAR(50),
   Orgs               VARCHAR(50),
   ChkOrgTele         VARCHAR(50),
   ChkedOrgNm         VARCHAR(100),
   ChkedOrgNo         VARCHAR(50),
   ChkCont            VARCHAR(500),
   ChkOrgNm           VARCHAR(100),
   ChkOrgNo           VARCHAR(50),
   ChkDt              VARCHAR(50),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          VARCHAR(50),
   constraint P_Key_1 primary key (ID)
);


create table BS_NoGenerate
(
   ID                   VARCHAR(50)       not null,
   OrgNo              VARCHAR(50),
   OrgNm              VARCHAR(100),
   Area               VARCHAR(50),
   AENoText           VARCHAR(50),
   AENoYear           VARCHAR(50),
   AENoIndex          BIGINT,
   EvdcNoText         VARCHAR(50),
   EvdcNoYear         VARCHAR(50),
   EvdcNoIndex        BIGINT,
   FactNoText         VARCHAR(50),
   FactNoYear         VARCHAR(50),
   FactNoIndex        BIGINT,
   AwayNoText         VARCHAR(50),
   AwayNoYear         VARCHAR(50),
   AwayNoIndex        BIGINT,
   comeinnotext       VARCHAR(50),
   comeinnoyear       VARCHAR(50),
   comeinindex        BIGINT,
   PbnshNoText        VARCHAR(50),
   PbnshNoYear        VARCHAR(50),
   PbnshNoIndex       BIGINT,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);
DROP TABLE BS_DEPT;
CREATE TABLE BS_DEPT ( NO VARCHAR(30) NOT NULL, NAME VARCHAR(100) DEFAULT NULL, ORGNO VARCHAR(50) DEFAULT NULL );
DROP TABLE BS_ATTCACHMENTS;
CREATE TABLE BS_ATTCACHMENTS ( ID VARCHAR(50) NOT NULL, WFID VARCHAR(50), ATTCHNAME VARCHAR(200), ATTCHUPLOADER VARCHAR(50), ATTCHPATH VARCHAR(100), ATTCHOFFLOW VARCHAR(50), CREATEDATE DATE, UPDATEDATE DATE, STATUS VARCHAR(50), FLAG VARCHAR(50), COMPNO VARCHAR(50) );
DROP TABLE BS_LOG;
CREATE TABLE BS_LOG ( ID VARCHAR(50) NOT NULL, PROCESSER VARCHAR(100) DEFAULT NULL, MESSAGE VARCHAR(1000) DEFAULT NULL, CREATEDATE TIMESTAMP, PRIORITY VARCHAR(10) DEFAULT NULL, CATEGORY VARCHAR(500) DEFAULT NULL, THREAD VARCHAR(500) DEFAULT NULL );
DROP TABLE BS_MESSAGE;
CREATE TABLE BS_MESSAGE ( ID VARCHAR(50) NOT NULL, TITLE VARCHAR(200), MESSAGE VARCHAR(1000), RECEIVER VARCHAR(50), SENDER VARCHAR(50), CREATEDATE TIMESTAMP, STATUS VARCHAR(10) DEFAULT 'NULL', FLAG VARCHAR(10), CATEGORY VARCHAR(100), RECEIVEDATE TIMESTAMP );
DROP TABLE BS_ORG;
CREATE TABLE BS_ORG ( NO VARCHAR(50) NOT NULL, NAME VARCHAR(200) DEFAULT NULL, PARENTNO VARCHAR(50) DEFAULT NULL, PARENTNAME VARCHAR(200) DEFAULT NULL, ORGTYPENO VARCHAR(50) DEFAULT NULL, ORGTYPENAME VARCHAR(200) DEFAULT NULL, ISPCB VARCHAR(5) DEFAULT NULL, STATUS VARCHAR(50) DEFAULT NULL );
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1010251000016', '中国工商银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1010251000016', '工商银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030451000012', '华夏银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030451000012', '华夏银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1010351000018', '中国农业银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1010351000018', '农业银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C3215651000019', '四川省农村信用社联合社', 'A1000151000028', '中国人民银行成都分行', 'C3215651000019', '四川省农村信用社联合社', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030551000013', '中国民生银行成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030551000013', '民生银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1010451000013', '中国银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1010451000013', '中国银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030151000019', '交通银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1030151000019', '交通银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030651000019', '广发银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030651000019', '广发银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1091251000018', '上海银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1091251000018', '上海银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1093051000010', '浙江民泰商业银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1093051000010', '浙江民泰商业银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1010551000011', '中国建设银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1010551000011', '建设银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1093151000011', '重庆银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1093151000011', '重庆银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030751000015', '平安银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030751000015', '平安银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030851000016', '招商银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030851000016', '招商银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030251000010', '中信银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030251000010', '中信银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030351000011', '中国光大银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030351000011', '光大银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1030951000017', '兴业银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1030951000017', '兴业银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1031551000015', '恒丰银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1031551000015', '恒丰银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1031051000010', '上海浦东发展银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1031051000010', '上海浦东发展银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1031651000016', '浙商银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1031651000016', '浙商银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1031851000018', '渤海银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1031851000018', '渤海银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1040351000012', '中国邮政储蓄银行股份有限公司四川省分行', 'A1000151000028', '中国人民银行成都分行', 'C1040351000012', '中国邮政储蓄银行四川省分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1050151000011', '汇丰银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1050151000011', '汇丰银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1050251000012', '东亚银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1050251000012', '东亚银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1050351000013', '南洋商业银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1050351000013', '南洋银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1053151000017', '花旗银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1053151000017', '花旗银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1053351000019', '摩根大通银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1053351000019', '摩根大通银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1056151000019', '三菱东京日联银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1056151000019', '三菱东京日联银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1059351000016', '友利银行(中国)有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1059351000016', '友利银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1062151000016', '华侨银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1062151000016', '华侨银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1062251000017', '大华银行(中国)有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1062251000017', '大华银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1066151000015', '苏格兰皇家银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1066151000015', '苏格兰皇家银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1067151000017', '渣打银行（中国）有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1067151000017', '渣打银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1080351000013', '天津银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1080351000013', '天津银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1080551000018', '贵阳银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1080551000018', '贵阳银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1081051000015', '大连银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1081051000015', '大连银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1085551000013', '哈尔滨银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1085551000013', '哈尔滨银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1087651000017', '包商银行股份有限公司成都分行', 'A1000151000028', '中国人民银行成都分行', 'C1087651000017', '包商银行成都分行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079351000014', '乐山市商业银行股份有限公司', 'A1000151000244', '中国人民银行乐山市中心支行', 'C1079351000014', '乐山商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079451000015', '德阳银行股份有限公司', 'A1000151000079', '中国人民银行德阳市中心支行', 'C1079451000015', '德阳银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1078951000018', '自贡市商业银行股份有限公司', 'A1000151000713', '中国人民银行自贡市中心支行', 'C1078951000018', '自贡商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079551000016', '绵阳市商业银行股份有限公司', 'A1000151000131', '中国人民银行绵阳市中心支行', 'C1079551000016', '绵阳商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079651000043', '南充市商业银行股份有限公司', 'A1000151000220', '中国人民银行南充市中心支行', 'C1079651000043', '南充商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079051000011', '攀枝花市商业银行股份有限公司', 'A1000151000232', '中国人民银行攀枝花市中心支行', 'C1079051000011', '攀枝花商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079151000012', '泸州市商业银行股份有限公司', 'A1000151000117', '中国人民银行泸州市中心支行', 'C1079151000012', '泸州商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079751000020', '达州市商业银行股份有限公司', 'A1000151000067', '中国人民银行达州市中心支行', 'C1079751000020', '达州商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079251000013', '宜宾市商业银行股份有限公司', 'A1000151000699', '中国人民银行宜宾市中心支行', 'C1079251000013', '宜宾商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1078851000017', '成都银行股份有限公司', 'A1000151000410', '中国人民银行成都分行营业管理部', 'C1078851000017', '成都银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079851000019', '雅安市商业银行股份有限公司', 'A1000151000257', '中国人民银行雅安市中心支行', 'C1079851000019', '雅安商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1080051000013', '遂宁市商业银行股份有限公司', 'A1000151000269', '中国人民银行遂宁市中心支行', 'C1080051000013', '遂宁商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1079951000034', '凉山州商业银行股份有限公司', 'A1000151000042', '中国人民银行凉山彝族自治州中心支行', 'C1079951000034', '凉山州商业银行', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('C1258851000018', '成都农村商业银行股份有限公司', 'A1000151000410', '中国人民银行成都分行营业管理部', 'C1258851000018', '成都农村商业银行 ', 'NO', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000410', '中国人民银行成都分行营业管理部', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000713', '中国人民银行自贡市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000232', '中国人民银行攀枝花市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000117', '中国人民银行泸州市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000079', '中国人民银行德阳市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000131', '中国人民银行绵阳市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000105', '中国人民银行广元市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000244', '中国人民银行乐山市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000269', '中国人民银行遂宁市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000182', '中国人民银行内江市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000220', '中国人民银行南充市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000129', '中国人民银行眉山市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000699', '中国人民银行宜宾市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000093', '中国人民银行广安市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000030', '中国人民银行巴中市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000067', '中国人民银行达州市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000257', '中国人民银行雅安市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000701', '中国人民银行资阳市中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000055', '中国人民银行阿坝州中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000081', '中国人民银行甘孜藏族自治州中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000042', '中国人民银行凉山彝族自治州中心支行', 'A1000151000028', '中国人民银行成都分行', '', '', 'YES', null);
insert into BS_ORG (NO, NAME, PARENTNO, PARENTNAME, ORGTYPENO, ORGTYPENAME, ISPCB, STATUS) values ('A1000151000028', '中国人民银行成都分行', 'A1000111000031', '', '', '', 'YES', null);
DROP TABLE BS_USERAPPLYINFO;
CREATE TABLE BS_USERAPPLYINFO ( ID VARCHAR(50) NOT NULL, NAME VARCHAR(20), ZXNAME VARCHAR(20), CARDTYPE VARCHAR(20), CARDID VARCHAR(50), JGPRINCIPAL VARCHAR(50), ZXPRINCIPAL VARCHAR(200), USERCODE VARCHAR(50), EDUCATION VARCHAR(20), POST VARCHAR(90), ORGCODEOFJR VARCHAR(50), ORGCODEOFXY VARCHAR(50), ORGCODEOFZZ VARCHAR(50), ORGNAME VARCHAR(150), PHONE VARCHAR(20), PHOTO VARCHAR(200), CREATERCODE VARCHAR(50), CREATEDATE DATE, STATUS VARCHAR(50), FLAG VARCHAR(50), PASSWORD VARCHAR(50), CREATERORGNO VARCHAR(50), MANAGERCODE VARCHAR(20) );
DROP TABLE BS_USERINFO;
CREATE TABLE BS_USERINFO ( ID VARCHAR(50) NOT NULL, NAME VARCHAR(20), ZXNAME VARCHAR(20), CARDTYPE VARCHAR(20), CARDID VARCHAR(50), JGPRINCIPAL VARCHAR(50), ZXPRINCIPAL VARCHAR(200), USERCODE VARCHAR(50), EDUCATION VARCHAR(20), POST VARCHAR(100), ORGCODEOFJR VARCHAR(50), ORGCODEOFXY VARCHAR(50), ORGCODEOFZZ VARCHAR(50), ORGNAME VARCHAR(150), PHONE VARCHAR(20), PHOTO VARCHAR(200), USERSTATUS VARCHAR(20), LOGINDATE TIMESTAMP, MANAGERCODE VARCHAR(50), CREATERCODE VARCHAR(50), CREATEDATE TIMESTAMP, STOPERCODE VARCHAR(50), STOPDATE TIMESTAMP, STATUS VARCHAR(50), FLAG VARCHAR(50), PASSWORD VARCHAR(32) );
insert into BS_USERINFO (ID, NAME, ZXNAME, CARDTYPE, CARDID, JGPRINCIPAL, ZXPRINCIPAL, USERCODE, EDUCATION, POST, ORGCODEOFJR, ORGCODEOFXY, ORGCODEOFZZ, ORGNAME, PHONE, PHOTO, USERSTATUS, LOGINDATE, MANAGERCODE, CREATERCODE, CREATEDATE, STOPERCODE, STOPDATE, STATUS, FLAG, PASSWORD) values ('51082419851031575Y', '邱洋', '邱洋', '身份证', '51082419851031575Y', '管理员', '企业征信管理员用户,个人征信管理员用户,', null, '大学本科', '科长', 'A1000151000410', 'A1000151000410', 'A1000151000410', '中国人民银行成都分行营业管理部', '15884448684', null, '启用', null, '51082419851031575X', '51082419851031575X', '2014-07-15 21:24:43', '', null, null, null, null);
insert into BS_USERINFO (ID, NAME, ZXNAME, CARDTYPE, CARDID, JGPRINCIPAL, ZXPRINCIPAL, USERCODE, EDUCATION, POST, ORGCODEOFJR, ORGCODEOFXY, ORGCODEOFZZ, ORGNAME, PHONE, PHOTO, USERSTATUS, LOGINDATE, MANAGERCODE, CREATERCODE, CREATEDATE, STOPERCODE, STOPDATE, STATUS, FLAG, PASSWORD) values ('C1078851000017-1', '刘洋', '刘洋', '身份证', '51082419851031575Z', '内控监督员', '企业征信管理员用户,个人征信管理员用户,', 'C1078851000017-1', '大学本科', '科长', 'C1078851000017', 'C1078851000017', 'C1078851000017', '成都银行股份有限公司', '15884448684', null, '启用', null, '51082419851031575Y', '51082419851031575Y', '2014-07-15 21:34:44', '', null, null, null, null);
insert into BS_USERINFO (ID, NAME, ZXNAME, CARDTYPE, CARDID, JGPRINCIPAL, ZXPRINCIPAL, USERCODE, EDUCATION, POST, ORGCODEOFJR, ORGCODEOFXY, ORGCODEOFZZ, ORGNAME, PHONE, PHOTO, USERSTATUS, LOGINDATE, MANAGERCODE, CREATERCODE, CREATEDATE, STOPERCODE, STOPDATE, STATUS, FLAG, PASSWORD) values ('51082419851031575A', '王洋', '王洋', '身份证', '51082419851031575A', '管理员', '企业征信管理员用户,个人征信管理员用户,', null, '大学本科', '科长', 'C1078851000017', 'C1078851000017', 'C1078851000017', '成都银行股份有限公司', '15884448684', null, '启用', null, '456', 'C1078851000017-1', '2014-07-15 00:00:00', '', null, null, null, null);
DROP TABLE BS_USERPLURALINFO;
CREATE TABLE BS_USERPLURALINFO ( ID VARCHAR(50) NOT NULL, ZXNAME VARCHAR(20), JGPRINCIPAL VARCHAR(50), ZXPRINCIPAL VARCHAR(200), ORGCODEOFXY VARCHAR(50), ORGCODEOFZZ VARCHAR(50), ORGNAME VARCHAR(150), STATUS VARCHAR(50), FLAG VARCHAR(50), LOGINID VARCHAR(50), ORGCODEOFJR VARCHAR(50) );

drop table IMPORTDEPTTEMP;
CREATE TABLE IMPORTDEPTTEMP (ID VARCHAR(50) NOT NULL, NAME VARCHAR(200) NOT NULL, ORGNO VARCHAR(50) NOT NULL, ORGNAME VARCHAR(200), 
FLAG VARCHAR(50), STATUS VARCHAR(50),constraint "A_Key_2" unique (ID));
ALTER TABLE IMPORTDEPTTEMP ADD CONSTRAINT PK_FA PRIMARY KEY (NAME,ORGNO) ;
