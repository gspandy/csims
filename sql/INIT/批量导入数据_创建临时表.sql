DROP TABLE
    IMPORTDEPTTEMP;
CREATE TABLE
    IMPORTDEPTTEMP
    (
        ID VARCHAR(50) NOT NULL,
        NAME VARCHAR(200) NOT NULL,
        ORGNO VARCHAR(50) NOT NULL,
        ORGNAME VARCHAR(200),
        FLAG VARCHAR(50),
        STATUS VARCHAR(50),
        CONSTRAINT PK_FA PRIMARY KEY (NAME, ORGNO),
        UNIQUE (ID)
    );
DROP TABLE
    IMPORTUSERTEMP;
CREATE TABLE
    IMPORTUSERTEMP
    (
        ID VARCHAR(50) NOT NULL,
        A VARCHAR(100),
        B VARCHAR(100),
        C VARCHAR(100),
        D VARCHAR(100),
        E VARCHAR(100),
        F VARCHAR(100),
        G VARCHAR(100),
        H VARCHAR(100),
        I VARCHAR(100),
        J VARCHAR(100),
        K VARCHAR(100),
        L VARCHAR(100),
        M VARCHAR(100),
        N VARCHAR(100),
        O VARCHAR(100),
        P VARCHAR(100),
        Q VARCHAR(100),
        R VARCHAR(100),
        S VARCHAR(100),
        T VARCHAR(100),
        CONSTRAINT P_KEY_1 PRIMARY KEY (ID)
    );
DROP TABLE
    IMPORTUSERTEMP_ERROR;
CREATE TABLE
    IMPORTUSERTEMP_ERROR
    (
        ID VARCHAR(50) NOT NULL,
        A VARCHAR(100),
        B VARCHAR(100),
        C VARCHAR(100),
        D VARCHAR(100),
        E VARCHAR(100),
        F VARCHAR(100),
        G VARCHAR(100),
        H VARCHAR(100),
        I VARCHAR(100),
        J VARCHAR(100),
        K VARCHAR(100),
        L VARCHAR(100),
        M VARCHAR(100),
        N VARCHAR(100),
        O VARCHAR(100),
        P VARCHAR(100),
        Q VARCHAR(100),
        R VARCHAR(100),
        S VARCHAR(100),
        T VARCHAR(100),
        MESSAGE VARCHAR(1000),
        CONSTRAINT P_KEY_1 PRIMARY KEY (ID)
    );
CREATE TABLE
    IMPORTTEMP_jguard
    (
        ID VARCHAR(50) NOT NULL,
        LOGIN VARCHAR(50) NOT NULL,
        NAME VARCHAR(255),
        PWD VARCHAR(255),
        ISPCBUSER VARCHAR(255),
        ORGNO VARCHAR(255),
        CONSTRAINT CC1409068106669 PRIMARY KEY (ID),
        UNIQUE (LOGIN)
    );
    