package net.sweet.test;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import junit.framework.TestCase;

public class StringUtilsTestCase extends TestCase {

	public void testFormatWord() {
		String content = "<P class=MsoNormal style= \"MARGIN: 0cm 0cm 0pt; TEXT-INDENT: 21pt; mso-char-indent-count: 1.5; mso-char-indent-size: 14.0pt\"><SPAN style=\"FONT-SIZE: 14pt; COLOR: black; FONT-FAMILY: 仿宋_GB2312; mso-bidi-font-size: 12.0pt; mso-hansi-font-family: 宋体\">一、贷款客户吴晋中<SPAN lang=EN-US>(身份证号码51082419681016259X)、徐涛(身份证号码513722197905170022)授权“审核本人作为担保人”进行查询，个人征信系统用户20090819430查询时选择查询原因为“贷款审批”。<?xml:namespace prefix = o ns = \"urn:schemas-microsoft-com:office:office\" /><o:p></o:p></SPAN></SPAN></P><SPAN style=\"FONT-SIZE: 14pt; COLOR: black; FONT-FAMILY: 仿宋_GB2312; mso-bidi-font-size: 12.0pt; mso-font-kerning: 1.0pt; mso-hansi-font-family: 宋体; mso-ansi-language: EN-US; mso-fareast-language: ZH-CN; mso-bidi-font-family: ''Times New Roman''; mso-bidi-language: AR-SA\">&nbsp; 二、企业征信系统用户<SPAN lang=EN-US>psbcsc_liuxl查询广元市清江旅游开发有限公司（中征码5108230000021438）的企业信用报告查询原因为“其他理由”，授权书勾选的查询用途为“审核本单位作为担保人”。</SPAN></SPAN>";
		String txtcontent = content.replaceAll("</?[^>]+>", "").replaceAll("<a>\\s*|\t|\r|\n</a>", "")
		        .replace("&nbsp;", " ");// 去除字符串中的空格,回车,换行符,制表符
		System.out.println(txtcontent);
	}

	public void testStringEscape() {
		System.out.println(StringEscapeUtils.escapeJava("zhong中华人民共和国"));
		System.out.println(StringEscapeUtils.unescapeJava("zhong\u4E2D\u534E\u4EBA\u6C11\u5171\u548C\u56FD"));

		System.out.println(StringEscapeUtils.unescapeJava("zhong中华人民共和国<dsf>"));
	}

	public void testString() {
		String a = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		System.out.println(StringUtils.rightPad(a, 1));
	}

	public void testLength() {
		String as = "一二三四五六七八九十一二a四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十";
		
		System.out.println(as.getBytes().length);
	}
}
