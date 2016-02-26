DROP TABLE JG_CREDENTIAL;
CREATE TABLE JG_CREDENTIAL
    (
        ID BIGINT NOT NULL,
        USER_ID BIGINT NOT NULL,
        PUBLIC_VISIBILITY INTEGER NOT NULL,
        CRED_NAME VARCHAR(255) NOT NULL,
        CRED_VALUE VARCHAR(255) NOT NULL,
        PRIMARY KEY (ID)
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        3,1,0,
        'password',
        '96e79218965eb72c92a549dd5a330112'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        4,1,1,
        'organization',
        'A1000151000028'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        7,2,0,
        'login',
        'guest'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        8,2,0,
        'password',
        'guest'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        9,3,1,
        'nickname',
        'systemAdmin1'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        10,3,0,
        'login',
        'systemadmin'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        11,3,0,
        'password',
        '96e79218965eb72c92a549dd5a330112'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        1,1,1,
        'nickname',
        'admin'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        2,1,0,
        'login',
        'admin'
    );
INSERT
INTO JG_CREDENTIAL
    (
        ID,
        USER_ID,
        PUBLIC_VISIBILITY,
        CRED_NAME,
        CRED_VALUE
    )
    VALUES
    (
        12,1,1,
        'ispcbuser',
        'YES'
    );
DROP TABLE JG_USER_PRINCIPAL;
CREATE TABLE JG_USER_PRINCIPAL
    (
        USER_ID BIGINT NOT NULL,
        PRINCIPAL_ID BIGINT NOT NULL,
        DEFINITION VARCHAR(255),
        ACTIVE INTEGER NOT NULL,
        PRIMARY KEY (PRINCIPAL_ID,USER_ID)
    );
INSERT
INTO JG_USER_PRINCIPAL
    (
        USER_ID,
        PRINCIPAL_ID,
        DEFINITION,
        ACTIVE
    )
    VALUES
    (
        1,1,
        'true',
        1
    );
INSERT
INTO JG_USER_PRINCIPAL
    (
        USER_ID,
        PRINCIPAL_ID,
        DEFINITION,
        ACTIVE
    )
    VALUES
    (
        2,2,
        'true',
        1
    );
INSERT
INTO JG_USER_PRINCIPAL
    (
        USER_ID,
        PRINCIPAL_ID,
        DEFINITION,
        ACTIVE
    )
    VALUES
    (
        3,3,
        'true',
        1
    );
DROP TABLE JG_USER;
CREATE TABLE JG_USER
    (
        ID BIGINT NOT NULL,
        PRIMARY KEY (ID)
    );
INSERT
INTO JG_USER
    (
        ID
    )
    VALUES
    (
        1
    );
INSERT
INTO JG_USER
    (
        ID
    )
    VALUES
    (
        2
    );
INSERT
INTO JG_USER
    (
        ID
    )
    VALUES
    (
        3
    );
ALTER TABLE JG_CREDENTIAL ADD CONSTRAINT FK_CREDENTIAL_USER_ID FOREIGN KEY (USER_ID) REFERENCES
    JG_USER (ID );
ALTER TABLE JG_USER_PRINCIPAL ADD CONSTRAINT FK_PRINCIPAL_USER_PRINCIPAL FOREIGN KEY (USER_ID)
    REFERENCES JG_USER (ID);
ALTER TABLE JG_USER_PRINCIPAL ADD CONSTRAINT FK_USER_USER_PRINCIPAL FOREIGN KEY (PRINCIPAL_ID)
    REFERENCES JG_PRINCIPAL (ID);
    
CREATE SEQUENCE jg_user_seq;
CREATE SEQUENCE jg_credential_seq;

ALTER SEQUENCE jg_credential_seq RESTART WITH 1000;
ALTER SEQUENCE jg_user_seq RESTART WITH 100;

DELETE
FROM BS_USERINFOOFJG;
DELETE
FROM BS_USERINFOOFZX;