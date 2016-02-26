package net.sweet.test.exam;

import net.sweet.test.base.ApplicationContextTest;

import com.gtm.csims.business.exam.ExamService;

public class ExamServiceTestCase extends ApplicationContextTest {

    private ExamService examService;

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public void testGetRecentScore() {
        System.out.println(examService.getRecentScore("511111111111101",
                "企业征信查询用户"));
        System.out.println(examService.getRecentScore("510504198506070639",
                "企业征信查询用户"));
        System.out.println(examService.getRecentScore("510504198506070639",
                "个人征信查询用户"));
    }
}
