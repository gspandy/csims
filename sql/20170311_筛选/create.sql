--==============================================================
-- Table: BS_AEPeopleJoinHistory
--==============================================================
create table BS_AEPeopleJoinHistory
(
   ID                   VARCHAR(50)            not null,
   PepName              VARCHAR(50),
   CARDID               VARCHAR(30),
   CertTp               VARCHAR(50),
   CertNo               VARCHAR(50),
   OrgNo                VARCHAR(50),
   OrgNm                VARCHAR(100),
   DptNm                VARCHAR(100),
    

   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(1000)          not null,
   AEEDORGNO            VARCHAR(1000)          not null,
   Pricipal             INTEGER,
   aeEnforceCreateDate  TIMESTAMP,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   
   
   Stat                 VARCHAR(50),
   Flag                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEDATE           TIMESTAMP,

   constraint P_Key_1 primary key (ID)
);

--==============================================================
-- Table: BS_AEedOrgJoinHistory
--==============================================================
create table BS_AEedOrgJoinHistory
(
   ID                   VARCHAR(50)            not null,

   AENO                 VARCHAR(50)            not null,
   AEORGNM              VARCHAR(100)           not null,
   AEORGNO              VARCHAR(50)            not null,
   AEEDORGNM            VARCHAR(1000)          not null,
   AEEDORGNO            VARCHAR(1000)          not null,
   AEEDORGTYPENO        VARCHAR(50),
   Pricipal             INTEGER,
   aeEnforceCreateDate  TIMESTAMP,
   AEPLANSTDATE         TIMESTAMP,
   AEPLANTMDATE         TIMESTAMP,
   
   
   Stat                 VARCHAR(50),
   Flag                 VARCHAR(50),
   CREATEDATE           TIMESTAMP,
   UPDATEDATE           TIMESTAMP,

   constraint P_Key_1 primary key (ID)
);