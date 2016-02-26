package net.sweet.test.jdbc;

import net.sweet.test.base.ApplicationContextTest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsLogDAO;
import com.gtm.csims.log.service.LogService;

public class HibernateAndJdbcTemplateTestCase extends ApplicationContextTest {
    private BsLogDAO bsLogDao;
    private JdbcTemplate jdbcTemplate;

    private LogService logService;

    public void setBsLogDao(BsLogDAO bsLogDao) {
        this.bsLogDao = bsLogDao;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    @Transactional
    public void test_transaction() {
    }

}
