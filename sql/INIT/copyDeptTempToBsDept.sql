INSERT INTO bs_dept (id,name,orgno,orgname)   
    SELECT id,name,orgno,orgname FROM IMPORTDEPTTEMP;