UPDATE
    BS_AEINSPECTION_ANL a
SET
    a.aeorgno =
    (
        SELECT
            no
        FROM
            bs_org
        WHERE
            name = a.a1) ,
    a.aeorgnm = a.a1,
    a.aeedorgno =
    (
        SELECT
            no
        FROM
            bs_org
        WHERE
            name = a.b1) ,
    a.aeedorgnm = a.b1
WHERE
    a.aeorgno IS NULL
OR  a.aeorgno = '';    
    
    
UPDATE
    BS_WORKBASIS a
SET
    a.aeorgno =
    (
        SELECT
            aeorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeorgnm =
    (
        SELECT
            aeorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeedorgno =
    (
        SELECT
            aeedorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeedorgnm =
    (
        SELECT
            aeedorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7)
WHERE
    a.aeorgno IS NULL
OR  a.aeorgno = '';




UPDATE
    BS_WORKCOMING a
SET
    a.aeorgno =
    (
        SELECT
            aeorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeorgnm =
    (
        SELECT
            aeorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeedorgno =
    (
        SELECT
            aeedorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7) ,
    a.aeedorgnm =
    (
        SELECT
            aeedorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED7)
WHERE
    a.aeorgno IS NULL
OR  a.aeorgno = '';







UPDATE
    BS_WORKGOAWAY a
SET
    a.aeorgno =
    (
        SELECT
            aeorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED14) ,
    a.aeorgnm =
    (
        SELECT
            aeorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED14) ,
    a.aeedorgno =
    (
        SELECT
            aeedorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED14) ,
    a.aeedorgnm =
    (
        SELECT
            aeedorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED14)
WHERE
    a.aeorgno IS NULL
OR  a.aeorgno = '';



UPDATE
    BS_WORKTALKSUMMARY a
SET
    a.aeorgno =
    (
        SELECT
            aeorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED16) ,
    a.aeorgnm =
    (
        SELECT
            aeorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED16) ,
    a.aeedorgno =
    (
        SELECT
            aeedorgno
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED16) ,
    a.aeedorgnm =
    (
        SELECT
            aeedorgnm
        FROM
            BS_AEINSPECTION
        WHERE
            INO = a.FILED16)
WHERE
    a.aeorgno IS NULL
OR  a.aeorgno = '';