package com.thinkgem.jeesite.modules.cms.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cms.dao.ResumeDao;
import com.thinkgem.jeesite.modules.cms.entity.Resume;
import com.thinkgem.jeesite.modules.cms.utils.ReceiveMailUtils;

@Service
@Lazy(false)
@Transactional(readOnly = true)
public class ResumeService extends CrudService<ResumeDao, Resume> {

	@Autowired
	private ResumeDao resumeDao;

	// @Scheduled(cron = "0 0/1 14,19 * * ?")
	// @Scheduled(cron = "*/5 * * * * ?")//每隔五秒执行一次
	//@Scheduled(cron = "0 0/1 * * * ?")
	// 每分钟执行一次
	@Transactional(readOnly = false)
	public void load() throws Exception {

		long startTime = System.currentTimeMillis();

		List<Resume> resumes = ReceiveMailUtils.receive();
		for (Resume resume : resumes) {
			resume.preInsert();
		}
		resumeDao.insert(resumes);

		long endTime = System.currentTimeMillis();

		float seconds = (endTime - startTime) / 1000F;

		System.out.println(Float.toString(seconds) + " seconds.");
		
		System.out.println("每分钟执行一次");
	}

	public Page<Resume> findPage(Page<Resume> page, Resume resume) {
		resume.getSqlMap().put("dsf",
				dataScopeFilter(resume.getCurrentUser(), "o", "u"));
		resume.setPage(page);

		List<Resume> resumes = dao.findList(resume);
		for (Resume re : resumes) {

			String resumeURL = re.getResumeURL();
			if (StringUtils.isNotEmpty(resumeURL)) {
				resumeURL = Global.getConfig("file.resume.server")
						+ Global.getConfig("file.resume.repository")
						+ resumeURL;
			} else {
				resumeURL = Global.getConfig("file.resume.server")
						+ Global.getConfig("file.resume.repository")
						+ Global.getConfig("file.resume.default.html");
			}
			re.setResumeURL(resumeURL);
		}

		page.setList(resumes);

		return page;
	}
}
