drop table BS_ADMENFORCE;
create table BS_ADMENFORCE
(
   ID                   VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(1000)          not null,
   AEEDORGNO            VARCHAR(1000)          not null,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   PRJNM                VARCHAR(200),
   PRJBASIS             VARCHAR(500),
   AEBASIS              VARCHAR(500),
   AECONTENT            VARCHAR(3000),
   AELIMT               VARCHAR(100),
   AEWAY                VARCHAR(50),
   AEPEOPLE             VARCHAR(100),
   AEPLAN               VARCHAR(500),
   DPTOPNION            VARCHAR(500),
   DPTPEOPLE            VARCHAR(50),
   DPTPEOPLEORG         VARCHAR(100),
   DPTPEOPLEORGNO       VARCHAR(50),
   CHAIROPNION          VARCHAR(500),
   CHAIRPEOPLE          VARCHAR(50),
   CHAIRPEOPLEORG       VARCHAR(100),
   CHAIRPEOPLEORGNO     VARCHAR(50),
   AESTAT               VARCHAR(20),
   REGPEP               VARCHAR(20),
   REGORGNM             VARCHAR(100),
   REGORGNO             VARCHAR(50),
   REGDT                TIMESTAMP,
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEDATE           TIMESTAMP,
   MAXINO               INTEGER,
   AESUMMARY            VARCHAR(500),
   STEP                 VARCHAR(5),
   AEHEADMAN            VARCHAR(20),
   AEOTHER              VARCHAR(20),
   AEMASTER             VARCHAR(20),
   CHAIRMAN             VARCHAR(50),
   DEPTMAN              VARCHAR(50),
   DEPTAUDITDATE        TIMESTAMP,
   CHAIRAUDITDATE       TIMESTAMP,
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_2" unique (AENO)
);


drop table BS_ADMPUNISH;
create table BS_ADMPUNISH
(
   ID                   VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(100)           not null,
   AEEDORGNO            VARCHAR(50)            not null,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   AEOPNIONNO           VARCHAR(50),
   PUNISHNO             VARCHAR(50)            not null,
   ILLEGALUNIT          VARCHAR(200),
   ILLEGALPEOPLE        VARCHAR(500),
   REASON               VARCHAR(500),
   SUMMARYS             CLOB,
   SUMRYATTA            VARCHAR(50),
   DPTOPNION            CLOB,
   PEOPLEOPNION         CLOB,
   CHAIROPNION          CLOB,
   DPTER                VARCHAR(50),
   CHAIRER              VARCHAR(50),
   PEOPLER              VARCHAR(50),
   DPTERDATE            TIMESTAMP,
   CHAIRERDATE          TIMESTAMP,
   PEOPLERDATE          TIMESTAMP,
   CREATOR              VARCHAR(50),
   CREATORORG           VARCHAR(100),
   CREATORORGNO         VARCHAR(50),
   CRTDATE              TIMESTAMP,
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEATE            TIMESTAMP,
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_3" unique (PUNISHNO)
);



drop table BS_ADMPUNISHCONS;
create table BS_ADMPUNISHCONS
(
   ID                   VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(100)           not null,
   AEEDORGNO            VARCHAR(50)            not null,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   AEOPNIONNO           VARCHAR(50)            not null,
   PUNISHNO             VARCHAR(50)            not null,
   PUNISHBOOKNO         VARCHAR(50)            not null,
   ENFORCEUNIT          VARCHAR(100),
   ENFORCEUNITNO        VARCHAR(50),
   PUNISHUNIT           VARCHAR(100),
   PUNISHUNITNO         VARCHAR(50),
   PUNISHBOOKATTA       VARCHAR(50),
   CREATOR              VARCHAR(50),
   CREATORORG           VARCHAR(100),
   CREATORORGNO         VARCHAR(50),
   CRTDATE              TIMESTAMP,
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEATE            TIMESTAMP,
   PUNISHGZBOOKATTA     VARCHAR(50),
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_2" unique (PUNISHBOOKNO)
);



drop table BS_AECONCLUSION;
create table BS_AECONCLUSION
(
   ID                   VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(100)           not null,
   AEEDORGNO            VARCHAR(50)            not null,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   AEOPNIONNO           VARCHAR(50)            not null,
   AEOPNIONBOOK         VARCHAR(50),
   RELPEOPLES           VARCHAR(1000),
   CREATOR              VARCHAR(50),
   CREATORORG           VARCHAR(100),
   CREATORORGNO         VARCHAR(50),
   CRTDATE              TIMESTAMP,
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEATE            TIMESTAMP,
   RELORGNM             VARCHAR(1000),
   DECISION             VARCHAR(50),
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_2" unique (AEOPNIONNO)
);




drop table BS_AEINSPECTION;
create table BS_AEINSPECTION
(
   ID                   VARCHAR(50)            not null,
   INO                  VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(100)           not null,
   AEEDORGNO            VARCHAR(50)            not null,
   AERECORDNO           VARCHAR(50),
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   AEPRJNM              VARCHAR(100),
   AEINDATE             TIMESTAMP,
   AEOUTDATE            TIMESTAMP,
   AEPEOPLES            VARCHAR(100),
   AEBASIS              VARCHAR(50),
   ENQUIRERCD           VARCHAR(50),
   ACTUALRCDNO          VARCHAR(50),
   ACTUALRCD            VARCHAR(50),
   INRCD                VARCHAR(50),
   INRCDNO              VARCHAR(50),
   OUTRCD               VARCHAR(50),
   OUTRCDNO             VARCHAR(50),
   CREATOR              VARCHAR(50),
   CREATORORG           VARCHAR(100),
   CREATORORGNO         VARCHAR(50),
   CRTDATE              TIMESTAMP,
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEATE            TIMESTAMP,
   PROBSUMR             VARCHAR(500),
   AEHEADMAN            VARCHAR(20),
   AEMASTER             VARCHAR(20),
   AEREVIEWER           VARCHAR(20),
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_2" unique (INO)
);



drop table BS_AERECTIFICATION;
create table BS_AERECTIFICATION
(
   ID                   VARCHAR(50)            not null,
   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(100)           not null,
   AEEDORGNO            VARCHAR(50)            not null,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   AEOPNIONNO           VARCHAR(50),
   TRACKNO              VARCHAR(255)           not null,
   TRACKCONTEND         VARCHAR(500),
   TRACKATTA            VARCHAR(50),
   STAT                 VARCHAR(50),
   FLAG                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEATE            TIMESTAMP,
   rectificationStDate TIMESTAMP,
   rectificationTmDate TIMESTAMP,
   constraint P_KEY_1 primary key (ID),
   constraint "A_Key_2" unique (TRACKNO)
);
