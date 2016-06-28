package com.thinkgem.jeesite.modules.cms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static void main(String[] args) {
		String ss="更新时间：2016-06-12陈建泽 2年工作经验 | 男 |  242岁（1991年11月11日） (ID:345462588) 居住地： 杭州-西湖区 "
				+ "户　口： 温州 电　话： 13615850680（手机） E-mail： 381474944@qq.com 最近工作 [ 1 年4个月] 公　司： 金瓯专利有限公司 "
				+ "行　业： 外包服务 职　位： 项目专员 学历 学　历： 本科 专　业： 自动化 学　校： 绍兴文理学院 ";
		//String ssString="更新时间：2015-06-24郭振华 8-9年工作经验 | 男 |  32岁（1983年7月4日） (ID:29043173) 居住地： 上海-浦东新区 户　口： 长沙 电　话： 13482825247（手机） E-mail： jeromeguo@126.com 最近工作 [ 11个月]";
		//String ss = "简历名称：项目专员 1年 杭州智联招聘期望从事职业：项目专员/助理简历更新时间：2016.06.12李福临 手机：15888818943李福临手机：15888818943男    21岁(1995年6月)    2年工作经验    本科    未婚 现居住地：杭州 | 户口：黄石 | 团员  身份证：420222199506092431 手机：15888818943 E-mail：1933737345@qq.com";
		//String ssString="简历名称：Java开发工程师 1年 杭州智联招聘期望从事职业：Java开发工程师、互联网软件工程师简历更新时间：2016.06.12李红辰 手机：15267135915李红辰手机：15267135915男    24岁(1992年10月)    1年工作经验    本科    未婚 现居住地：杭州 | 户口：台州 | 团员  身份证：331004199210242512 手机：15267135915 E-mail：bb82701052@163.com";

		//String ssString="简历编号：36928297最新登录：2016-03-13个人信息姓名：颜浩健性别：男手机号码：18258289618年龄：26 岁电子邮件：234908180@qq.com教育程度：本科工作年限：3 年婚姻状况：保密职业状态：在职，看看新机会所在地：杭州国籍：中国";
		//猎聘Test
		System.out.println("LENGTH:"+ss.length());
		/*System.out.println("LENGTH:"+ssString.length());
	    
		System.out.println("RESUMENO:"+regex("(?<=简历编号：).+?(?=最新登录)", ssString));
		System.out.println("DEGREE:"+regex("(?<=教育程度：).+?(?=工作年限)", ssString));
		System.out.println("NAME:"+regex("(?<=姓名：).+?(?=性别：)",ssString));
		System.out.println("AGE:"+regex("(?<=年龄：).+?(?= 岁)", ssString));
		System.out.println("EMAIL:"+regex(PatternConstants.PATTERN_COMMON_WORD_EMAIL, ssString));
		System.out.println("PHONE:"+regex(PatternConstants.PATTERN_COMMON_WORD_PHONE, ssString));
		System.out.println("SEX:"+regex(PatternConstants.PATTERN_COMMON_WORD_SEX, ssString));*/
		
	/*	System.out.println("DEGREE:"+regex("(?<=工作经验    ).+?(?=    未婚 )", ss));
		
		System.out.println(regex("(?<=姓 名：).+?(?=工作年限)", ss).trim());
		System.out.println(regex("(?<=简历编号：).+?(?=最新登录)", ss.replace(" ", ""))
				.trim());
		System.out.println(regex("(?<=教育程度：).+?(?=户籍)", ss.replace(" ", ""))
				.trim());

		System.out.println(regex("(?<=电话：).+?(?=性别)", ss.replace(" ", ""))
				.trim());
		System.out.println(regex("(?<=年龄：).+?(?=国籍)", ss.replace(" ", ""))
				.trim());
		System.out.println(regex("(?<=性别：).+?(?=电子邮件)", ss.replace(" ", ""))
				.trim());
		System.out.println(regex("(?<=来自).+?(?=的候选人)", ss.replace(" ", ""))
				.trim());*/

		
	
		//System.out.println(regex("[男女]", ss));
	//	System.out.println(regex("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", ss));
		//System.out.println(regex("(?<!\\d)(?:(?:1[35]\\d{9})|(?:0[1-9]\\d{1,2}-?\\d{7,8}))(?!\\d)", ss));
		//System.out.println(regex("(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)", ss));
		
		
		//System.out.println(regex("\\w+(@+\\w+)+\\.\\w+", ss));//email
		
		System.out.println("resumeNO::"+regex("(?<=(ID:)).+?(?=\\) 居住地)", ss));//"(?<=姓 名：).+?(?=工作年限)"
		System.out.println("DEGREE::"+regex("(?<=学　历： ).+?(?=专　业)", ss));//"(?<=姓 名：).+?(?=工作年限)"
		//System.out.println("name::"+regex("(?<=(\\d+-\\d+-\\d+)).+?(?=(\\d+))", ss));//"(?<=姓 名：).+?(?=工作年限)"
		System.out.println("name::"+regex("(?<=(\\d+-\\d+-\\d+)).+?(?=(\\d+))", ss).substring(1));//"(?<=姓 名：).+?(?=工作年限)"
		System.out.println("DATE::"+regex("\\d+-\\d+-\\d+", ss));//"(?<=姓 名：).+?(?=工作年限)"
		//System.out.println("name::"+regex("\\d+工作经验", ss));//"(?<=姓 名：).+?(?=工作年限)"
		//System.out.println("my:::"+regex(PatternConstants.PATTERN_JOB51_WORD_EMAIL, ss));
		System.out.println(regex("\\d{1,3}岁", ss).substring(0, 2));
		//System.out.println(regex("(?<=(ID:).+?(?=))", ss));
		
		
		//String string="你好男34岁";
		//System.out.println(regex("[男]34", "catalog,cet,cft"));
		//System.out.println(regex("c[aec]t", "catalog,cet,cft"));
	}
	
	public static String regex(String patternString, String res) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher mr = pattern.matcher(res);
		String result = "";
		while (mr.find()) {
			result = mr.group().trim();
		}
		return result;
	}

}
