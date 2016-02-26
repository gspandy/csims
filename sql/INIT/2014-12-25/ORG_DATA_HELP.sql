SELECT *
FROM Bs_org
WHERE parentNo = '0';

SELECT *
FROM Bs_org
WHERE parentNo IN (
'PCB',
'GYSYYH',
'OTHER',
'QGXGFZSYYH',
'XFJRGS',
'ZCXYH',
'SWCSSYYH',
'CSSYYH',
'NCSYYH',
'NCXYHZS',
'GJJGLZX',
'CZYH',
'ZCGLGS',
'BXGS',
'XEDKGS',
'ZQGS',
'RJXDBGS',
'WZSYYH',
'ZXJG',
'DKGS',
'CWGS',
'XTGS'
);