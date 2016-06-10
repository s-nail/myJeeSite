package com.thinkgem.jeesite.modules.cms.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cms.entity.Resume;

@MyBatisDao
public interface ResumeDao extends CrudDao<Resume>{

	void insert(List<Resume> resumes);
}
