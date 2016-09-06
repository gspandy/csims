DROP TABLE BS_ANSWERRESULT;
CREATE TABLE BS_ANSWERRESULT ( ID VARCHAR(50) NOT NULL, BSQUESTIONAIRE VARCHAR(50), BSQUESTION VARCHAR(50), ARORGNO VARCHAR(50), ARORGNAME VARCHAR(500), ARORGTYPENO VARCHAR(50), ARORGTYPENAME VARCHAR(500), ARAREANO VARCHAR(50), ARAREA VARCHAR(500), ANSWERRESULT VARCHAR(5), STATUS VARCHAR(50), FLAG VARCHAR(50), CREATEDATE TIMESTAMP, UPDATEATE TIMESTAMP, CONSTRAINT P_KEY_1 PRIMARY KEY (ID) );

DROP TABLE BS_QUESTION;
CREATE TABLE BS_QUESTION ( ID VARCHAR(50) NOT NULL, BSQUESTIONAIRE VARCHAR(50), QQTITLE VARCHAR(1000), QQINDEX SMALLINT, ANSWERA VARCHAR(200), ANSWERB VARCHAR(200), ANSWERC VARCHAR(200), ANSWERD VARCHAR(200), ANSWERE VARCHAR(200), ANSWERF VARCHAR(200), ANSWERG VARCHAR(200), STATUS VARCHAR(50), FLAG VARCHAR(50), CREATEDATE TIMESTAMP, UPDATEATE TIMESTAMP, CONSTRAINT P_KEY_1 PRIMARY KEY (ID) );

DROP TABLE BS_QUESTIONAIRE;
CREATE TABLE BS_QUESTIONAIRE ( ID VARCHAR(50) NOT NULL, QTITLE VARCHAR(500), QSUMRY VARCHAR(1000), QCREATOR VARCHAR(50), QCREATORORGNAME VARCHAR(100), QCREATORORGNO VARCHAR(50), QENDDATETIME VARCHAR(20), STATUS VARCHAR(5), FLAG VARCHAR(50), CREATEDATE TIMESTAMP, UPDATEATE TIMESTAMP, CONSTRAINT P_KEY_1 PRIMARY KEY (ID) );

DROP TABLE BS_SURVEYOBJECT;
CREATE TABLE BS_SURVEYOBJECT ( ID VARCHAR(50) NOT NULL, BSQUESTIONAIRE VARCHAR(50), SOQORGNO VARCHAR(50), SOQORGNAME VARCHAR(500), SOQORGTYPENO VARCHAR(50), SOQORGTYPE VARCHAR(500), SOISFINISHED CHARACTER(1), STATUS VARCHAR(50), FLAG VARCHAR(50), CREATEDATE TIMESTAMP, UPDATEATE TIMESTAMP, CONSTRAINT P_KEY_1 PRIMARY KEY (ID) );


CREATE INDEX
    idx_qn
ON
    BS_ANSWERRESULT
    (
        BSQUESTIONAIRE
    );
