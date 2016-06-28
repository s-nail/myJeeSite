package com.thinkgem.jeesite.modules.cms.utils;

import com.thinkgem.jeesite.modules.cms.entity.Resume;
/*
 * Created by yuqiangjia on 2016/6/9.
 */
public class LiePinMatcher extends BaseMatcher {

	@Override
	public Resume parseEmail(String contect) {
		
		return null;
	}

	@Override
	public Resume parseWord(String contect) {
		Resume resume=new Resume();
		resume.setName(RegexUtils.regex(PatternConstants.PATTERN_LIEPIN_WORD_NAME, contect).substring(1));
		resume.setAge(RegexUtils.regex(PatternConstants.PATTERN_LIEPIN_WORD_AGE, contect).substring(0, 2));
		resume.setDegree(RegexUtils.regex(PatternConstants.PATTERN_LIEPIN_WORD_DEGREE, contect));
		resume.setEmail(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_EMAIL, contect));
		resume.setPhone(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_PHONE, contect));
		resume.setResumeNo(RegexUtils.regex(PatternConstants.PATTERN_LIEPIN_WORD_RESUME_NO, contect));
		resume.setSex(RegexUtils.regex(PatternConstants.PATTERN_COMMON_WORD_SEX, contect));
		resume.setSource(PatternConstants.LIEPIN_WORD_SOURCE);
		resume.setUnread(true);//51job  word是否已读  暂定
		return resume;
	}

}
