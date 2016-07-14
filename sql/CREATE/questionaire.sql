drop table BS_Questionaire;
create table BS_Questionaire
(
   ID                 VARCHAR(50)            not null,
   QTitle             VARCHAR(500),
   QSumry             VARCHAR(1000),
   QCreator            VARCHAR(50),
   QCreatorOrg         VARCHAR(100),
   QCreatorOrgNo       VARCHAR(50),
   QCrtDate            TIMESTAMP,
   QisEnable           char(1),
   QisFinished         char(1),
   QStat              SMALLINT,
   QendDateTime        TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   check(QisEnable in ('Y','N')),
   check(QisFinished in ('Y','N')),
   constraint P_Key_1 primary key (ID)
);
 

drop table BS_SurveyObject;
create table BS_SurveyObject
(
   ID                 VARCHAR(50)            not null,
   SOQuestionaireID     VARCHAR(50),
   SOQOrgNo             VARCHAR(50),
   SOQOrg               VARCHAR(500),
   SOQOrgTypeNo         VARCHAR(50),
   SOQOrgType           VARCHAR(500),
   SOisFinished         char(1),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   check(SOisFinished in ('Y','N')),
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_survey_qid ON BS_SurveyObject (SOQuestionaireID);
CREATE INDEX idx_survey_orgno ON BS_SurveyObject (SOQOrgNo);
CREATE INDEX idx_survey_orgtypeno ON BS_SurveyObject (SOQOrgTypeNo);

drop table BS_Question;
create table BS_Question
(
   ID                 VARCHAR(50)            not null,
   QQuestionaireID     VARCHAR(50), 
   QQTitle             VARCHAR(1000),
   QQIndex             SMALLINT,
   AnswerA            VARCHAR(200),
   AnswerB            VARCHAR(200),
   AnswerC            VARCHAR(200),
   AnswerD            VARCHAR(200),
   AnswerE            VARCHAR(200),
   AnswerF            VARCHAR(200),
   AnswerG            VARCHAR(200),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_ques_qid ON BS_Question (QQuestionaireID);

drop table BS_Answer;

drop table BS_AnswerResult;
create table BS_AnswerResult
(
   ID                 VARCHAR(50)            not null,
   ARQuestionaireID     VARCHAR(50),
   ARQuestionID         VARCHAR(50),
   AROrgNo             VARCHAR(50),
   AROrg               VARCHAR(500),
   AROrgTypeNo         VARCHAR(50),
   AROrgType           VARCHAR(500),
   ARAreaNo            VARCHAR(50),
   ARArea              VARCHAR(500),
   An_A               char(1),
   An_B               char(1),
   An_C               char(1),
   An_D               char(1),
   An_E               char(1),
   An_F               char(1),
   An_G               char(1),
   ARCreator            VARCHAR(50),
   ARCrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   check(An_A in ('Y','N')),
   check(An_B in ('Y','N')),
   check(An_C in ('Y','N')),
   check(An_D in ('Y','N')),
   check(An_E in ('Y','N')),
   check(An_F in ('Y','N')),
   check(An_G in ('Y','N')),
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_ar_qid_an ON BS_AnswerResult (ARQuestionaireID);
CREATE INDEX idx_ar_quesid ON BS_AnswerResult (ARQuestionID);