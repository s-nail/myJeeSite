package com.thinkgem.jeesite.modules.cms.utils;

import com.thinkgem.jeesite.modules.cms.entity.Resume;
/*
 * Created by yuqiangjia on 2016/6/9.
 */
public abstract class BaseMatcher {

	//解析邮箱中的邮件
	public abstract Resume parseEmail(String contect);

	//解析批量上传的word
	public abstract Resume parseWord(String contect);
}
