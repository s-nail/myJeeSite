package com.thinkgem.jeesite.modules.cms.utils;

/*
 * Created by yuqiangjia on 2016/6/25.
 */
public class PatternConstants {
	//51job word
	public static final String PATTERN_JOB51_WORD_NAME = "(?<=(\\d+-\\d+-\\d+)).+?(?=(\\d+))";//1张三	
	public static final String PATTERN_JOB51_WORD_RESUME_NO = "(?<=(ID:)).+?(?=\\) 居住地)";
	public static final String PATTERN_JOB51_WORD_AGE = "\\d{2}岁";//24岁
	public static final String PATTERN_JOB51_WORD_DEGREE = "(?<=学　历： ).+?(?=专　业)";	
	
	//智联 word
	public static final String PATTERN_ZHILIAN_WORD_NAME_FRIST = "(?<=(\\d+(-|.)\\d+(-|.)\\d+)).+?(?= 手机+)";
	public static final String PATTERN_ZHILIAN_WORD_NAME_SECOND = "(?<=(\\d{6})).+?(?=手机+)";
	public static final String PATTERN_ZHILIAN_WORD_AGE = "\\d{2}岁";//24岁
	public static final String PATTERN_ZHILIAN_WORD_DEGREE = "(?<=工作经验    ).+?(?=    未婚 )";		
	//智联姓名提取示范：
	/* String v=regex(PatternConstants.PATTERN_ZHILIAN_WORD_NAME_FRIST,ssString);
	   System.out.println("NAME:"+regex(PatternConstants.PATTERN_ZHILIAN_WORD_NAME_SECOND,v));*/	
	
	//猎聘 word
    public static final String PATTERN_LIEPIN_WORD_NAME = "(?<=姓名：).+?(?=性别：)";
	public static final String PATTERN_LIEPIN_WORD_RESUME_NO = "(?<=简历编号：).+?(?=最新登录)";
	public static final String PATTERN_LIEPIN_WORD_AGE = "(?<=年龄：).+?(?= 岁)";//24
	public static final String PATTERN_LIEPIN_WORD_DEGREE = "(?<=教育程度：).+?(?=工作年限)";	
	
	//共同
	public static final String PATTERN_COMMON_WORD_SEX = "[男女]";
	public static final String PATTERN_COMMON_WORD_PHONE = "(?<!\\d)(?:(?:1[358]\\d{9})|(?:861[358]\\d{9}))(?!\\d)";
	public static final String PATTERN_COMMON_WORD_EMAIL = "\\w+@\\w+.\\w+";//\\w+(@+\\w+)+\\.\\w+
	public static final String JOB51_WORD_SOURCE = "51job";
	public static final String ZHILIAN_WORD_SOURCE = "智联";
	public static final String LIEPIN_WORD_SOURCE = "猎聘";
	
	//猎聘email
    public static final String PATTERN_LIEPIN_EMAIL_NAME = "(?<=姓 名：).+?(?=工作年限)";
	public static final String PATTERN_LIEPIN_EMAIL_RESUME_NO = "(?<=简历编号：).+?(?=最新登录)";
	public static final String PATTERN_LIEPIN_EMAIL_AGE = "(?<=年龄：).+?(?=国籍)";
	public static final String PATTERN_LIEPIN_EMAIL_SEX = "(?<=性别：).+?(?=电子邮件)";
	public static final String PATTERN_LIEPIN_EMAIL_DEGREE = "(?<=教育程度：).+?(?=户籍)";	
	public static final String PATTERN_LIEPIN_EMAIL_SOURCE = "(?<=来自).+?(?=的候选人)";
	public static final String PATTERN_LIEPIN_EMAIL_PHONE = "(?<=电话：).+?(?=性别)";

}
