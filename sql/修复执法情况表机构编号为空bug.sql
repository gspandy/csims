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