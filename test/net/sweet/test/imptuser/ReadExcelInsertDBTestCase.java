package net.sweet.test.imptuser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sweet.test.base.ApplicationContextTest;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gtm.csims.utils.ExcelUtil;

/**
 * 
 * @author Sweet
 * 
 */
public class ReadExcelInsertDBTestCase extends ApplicationContextTest {
    private JdbcTemplate jdbcTemplate;

    private static final String FOLD_PATH = "E:\\MyWorkSpaces10\\csims\\sql\\20150309";

    /**
     * 从excel中读取源部门信息并插入到部门临时表（IMPORTDEPTTEMP）中.
     */
    public void _testReadDeptInsertImport() {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(FOLD_PATH + "\\total_dept.xls");
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= 10740; rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    try {
                        jdbcTemplate
                                .execute("INSERT INTO IMPORTDEPTTEMP (ID,NAME,ORGNO,ORGNAME) VALUES ('"
                                        + ExcelUtil.getValue(row.getCell(0))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(3))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(1))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(2)) + "')");
                    } catch (Exception e) {
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从excel中读取源用户信息并插入到用户临时表（IMPORTUSERTEMP）中.
     */
    public void _testReadUserInsertImport() {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(FOLD_PATH + "\\total_users.xls");
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 2; rowIndex <= 16771; rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    try {
                        jdbcTemplate
                                .execute("insert into IMPORTUSERTEMP (ID,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S) values ('"
                                        + ExcelUtil.getValue(row.getCell(19))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(0))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(1))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(2))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(3))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(4))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(5))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(6))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(7))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(8))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(9))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(10))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(11))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(12))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(13))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(14))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(15))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(16))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(17))
                                        + "','"
                                        + ExcelUtil.getValue(row.getCell(18)) + "')");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从excel中读取源征信执法检查编号规则并插入到BS_NOGENERATE中.
     */
    public void testReadNoGenerateInsertBS_NOGENERATE() {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(FOLD_PATH + "\\total_nogenerate.xls");
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= 101; rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    try {
                        for (int i = 2014; i <= 2016; i++) {
                            jdbcTemplate
                                    .execute("INSERT INTO BS_NOGENERATE (ID, ORGNO, ORGNM, AREA, AENOTEXT, AENOYEAR, AENOINDEX, EVDCNOTEXT, EVDCNOYEAR, EVDCNOINDEX, FACTNOTEXT"
                                            + ", FACTNOYEAR, FACTNOINDEX, AWAYNOTEXT, AWAYNOYEAR, AWAYNOINDEX, PBNSHNOTEXT, PBNSHNOYEAR, PBNSHNOINDEX, STAT, FLAG, CREATEDATE"
                                            + ", UPDATEATE, COMEINNOTEXT, COMEINNOYEAR, COMEININDEX) VALUES ('"
                                            + String.valueOf(i)
                                            + String.valueOf(rowIndex)
                                            + "', '"
                                            + ExcelUtil.getValue(row.getCell(1))
                                            + "', '"
                                            + ExcelUtil.getValue(row.getCell(2))
                                            + "', '"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "', '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "检立字"
                                            + "', '"
                                            + i
                                            + "', 1, '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "取证"
                                            + "', '"
                                            + i
                                            + "', 1, '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "认定"
                                            + "', '"
                                            + i
                                            + "', 1, '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "离场"
                                            + "', '"
                                            + i
                                            + "', 1, '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "罚立字"
                                            + "', '"
                                            + i
                                            + "', 1, null, null, null, null, '"
                                            + "（"
                                            + ExcelUtil.getValue(row.getCell(0))
                                            + "征信）"
                                            + "进场"
                                            + "', '"
                                            + i
                                            + "', 1)");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从excel中读取源用户考试成绩并更新到IMPORTUSERTEMP中.
     */
    public void _testReadScoreUpdateUserTemp() {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(FOLD_PATH + "\\total_2013score.xls");
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= 14411; rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    jdbcTemplate.update("UPDATE IMPORTUSERTEMP SET G='"
                            + ExcelUtil.getValue(row.getCell(3)).trim() + "' WHERE F = '"
                            + ExcelUtil.getValue(row.getCell(1)).trim() + "'");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从excel中读取源行政执法信息并插入到BS_AEPEOPLE表中.
     */
    public void _testReadAepeopleInsertAepeople() {
        InputStream is = null;
        HSSFWorkbook wb = null;
        try {
            is = new FileInputStream(FOLD_PATH + "\\total_aepeople.xls");
            POIFSFileSystem fs = new POIFSFileSystem(is);
            wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            for (int rowIndex = 1; rowIndex <= 281; rowIndex++) {
                HSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    try {
                        jdbcTemplate
                                .execute("INSERT INTO BS_AEPEOPLE (ID, PEPNAME, CARDID, CERTTP, CERTNO, BANKNO, BANKNM, CREDITNO"
                                        + ", ORGNO, ORGNM, DPTNM, DPTNO, PRCIPAL, TELE, EDUCATION, SEX, STAT, FLAG, CREATEDATE, UPDATEATE) VALUES ('"
                                        + rowIndex
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(3))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(6))
                                        + "', null, '"
                                        + ExcelUtil.getValue(row.getCell(7))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(0))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(1))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(7))
                                        + "', 'A1000151000028', '中国人民银行成都分行', '"
                                        + ExcelUtil.getValue(row.getCell(2))
                                        + "', null, '"
                                        + ExcelUtil.getValue(row.getCell(4))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(9))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(8))
                                        + "', '"
                                        + ExcelUtil.getValue(row.getCell(5))
                                        + "', null, null, null, null)");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
