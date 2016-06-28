package com.thinkgem.jeesite.modules.cms.utils;

import com.thinkgem.jeesite.modules.cms.entity.Resume;
/**
 * Created by yuqiangjia on 2016/6/9.
 */
public class ZhiLianMatcher extends BaseMatcher {

	@Override
	public Resume parseEmail(String contect) {

		return null;
	}

	@Override
	public Resume parseWord(String contect) {
		Resume resume=new Resume();
		
		String v=RegexUtils.regex(PatternConstants.PATTERN_ZHILIAN_WORD_NAME_FRIST,contect);
		resume.setName(RegexUtils.regex(PatternConstants.PATTERN_ZHILIAN_WORD_NAME_SECOND, v));
		resume.setAge(RegexUtils.regex(PatternConstants.PATTERN_ZHILIAN_WORD_AGE, contect).substring(0, 2));
		resume.setDegree(RegexUtils.regex(PatternConstants.PATTERN_ZHILIAN_WORD_DEGREE, contect));
		resume.setEmail(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_EMAIL, contect));
		resume.setPhone(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_PHONE, contect));
		//resume.setResumeNo(RegexUtils.regex(PatternConstants.PATTERN_ZHILIAN_WORD_RESUME_NO, contect));//智联word没有简历编号
		resume.setSex(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_SEX, contect));
		resume.setSource(PatternConstants.ZHILIAN_WORD_SOURCE);
		resume.setUnread(true);//51job  word是否已读暂定
		return resume;
	}

}
