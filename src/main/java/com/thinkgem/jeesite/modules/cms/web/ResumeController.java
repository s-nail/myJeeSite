package com.thinkgem.jeesite.modules.cms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.cms.entity.Resume;
import com.thinkgem.jeesite.modules.cms.service.ResumeService;

@Controller
@RequestMapping(value = "${adminPath}/cms/resume")
public class ResumeController {

	@Autowired
	private ResumeService resumeservice;

	@RequestMapping(value = "/list")	
	public String list(Resume resume, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<Resume> page = resumeservice.findPage(new Page<Resume>(request,
				response), resume);
		model.addAttribute("page", page);
		return "modules/cms/resumeList";
	}

	@RequestMapping(value="/import")
	public Object importResumes(HttpServletRequest request, Model model, RedirectAttributes redirectAttributes){
		
		System.out.println("导入页面");
		return "modules/cms/importWord";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public Object save(HttpServletRequest request, Model model,@RequestParam(value="files",required=false)MultipartFile[] file, RedirectAttributes redirectAttributes) {
		System.out.println("resume save");
		return null;
	}
}
