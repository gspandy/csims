drop table BS_Questionaire;
create table BS_Questionaire
(
   ID                 VARCHAR(50)            not null,
   QTitle             VARCHAR(500),
   DSumry             VARCHAR(1000),
   Creator            VARCHAR(50),
   CreatorOrg         VARCHAR(100),
   CreatorOrgNo       VARCHAR(50),
   CrtDate            TIMESTAMP,
   isEnable           char(1),
   isFinished         char(1),
   endDateTime        TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   check(isEnable in ('Y','N')),
   check(isFinished in ('Y','N')),
   constraint P_Key_1 primary key (ID)
);
 

drop table BS_SurveyObject;
create table BS_SurveyObject
(
   ID                 VARCHAR(50)            not null,
   QuestionaireID     VARCHAR(50),
   QOrgNo             VARCHAR(50),
   QOrg               VARCHAR(500),
   QOrgTypeNo         VARCHAR(50),
   QOrgType           VARCHAR(500),
   isFinished         char(1),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   check(isFinished in ('Y','N')),
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_survey_qid ON BS_SurveyObject (QuestionaireID);
CREATE INDEX idx_survey_orgno ON BS_SurveyObject (QOrgNo);
CREATE INDEX idx_survey_orgtypeno ON BS_SurveyObject (QOrgTypeNo);

drop table BS_Question;
create table BS_Question
(
   ID                 VARCHAR(50)            not null,
   QuestionaireID     VARCHAR(50), 
   QTitle             VARCHAR(1000),
   QIndex             SMALLINT,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_ques_qid ON BS_Question (QuestionaireID);

drop table BS_Answer;
create table BS_Answer
(
   ID                 VARCHAR(50)            not null,
   QuestionaireID     VARCHAR(50),
   QuestionID         VARCHAR(50), 
   QAnswerNo          CHAR(1),
   QAnswerTitle       VARCHAR(1000),
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_an_qid_an ON BS_Answer (QuestionaireID);
CREATE INDEX idx_an_quesid ON BS_Answer (QuestionID);

drop table BS_AnswerResult;
create table BS_AnswerResult
(
   ID                 VARCHAR(50)            not null,
   QuestionaireID     VARCHAR(50),
   QuestionID         VARCHAR(50),
   QAnswerID          VARCHAR(50),
   QOrgNo             VARCHAR(50),
   QOrg               VARCHAR(500),
   QOrgTypeNo         VARCHAR(50),
   QOrgType           VARCHAR(500),
   QAreaNo            VARCHAR(50),
   QArea              VARCHAR(500),
   QAnswerNo          CHAR(1),
   Creator            VARCHAR(50),
   CrtDate            TIMESTAMP,
   Stat               VARCHAR(50),
   Flag               VARCHAR(50),
   CreateDate         TIMESTAMP,
   Updateate          TIMESTAMP,
   constraint P_Key_1 primary key (ID)
);
CREATE INDEX idx_ar_qid_an ON BS_AnswerResult (QuestionaireID);
CREATE INDEX idx_ar_quesid ON BS_AnswerResult (QuestionID);
CREATE INDEX idx_ar_anid ON BS_AnswerResult (QAnswerID);