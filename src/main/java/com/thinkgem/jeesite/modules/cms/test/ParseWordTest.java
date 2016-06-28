package com.thinkgem.jeesite.modules.cms.test;

import java.io.FileInputStream;
import java.io.InputStream;

import com.thinkgem.jeesite.modules.cms.entity.Resume;
import com.thinkgem.jeesite.modules.cms.utils.BaseMatcher;
import com.thinkgem.jeesite.modules.cms.utils.FileTypeUtils;
import com.thinkgem.jeesite.modules.cms.utils.Html2MHTUtils;
import com.thinkgem.jeesite.modules.cms.utils.Job51Matcher;
import com.thinkgem.jeesite.modules.cms.utils.JsoupUtils;
import com.thinkgem.jeesite.modules.cms.utils.LiePinMatcher;
import com.thinkgem.jeesite.modules.cms.utils.UploadUtils;
import com.thinkgem.jeesite.modules.cms.utils.ZhiLianMatcher;

public class ParseWordTest {
	public static void main(String[] args) throws Exception {
		//InputStream is = new FileInputStream("D:\\word\\51job_陈建泽(345462588).doc");
		InputStream is = new FileInputStream("D:\\word\\智联招聘_李福临_中文_20160613_47337433.doc");
		//InputStream is = new FileInputStream("D:\\word\\简历编号36928297-颜浩健-.NET-猎聘网简历.doc");
		doWord(is);

	}
	public static Resume doWord(InputStream is) throws Exception{
		String type = FileTypeUtils.getFileType(is);
		BaseMatcher matcher;
		Resume resume = null;
		switch (type) {
		case "mht"://51job
			String mhtText=Html2MHTUtils.mht2html(is);//获得html文本内容
			String dbPath = Html2MHTUtils.saveHtml(mhtText,Resume.FILE_UPLOAD_RESUME);//保存html到本地，返回数据库存储路径
			String context=JsoupUtils.parseWord(mhtText).substring(0,250);//从html获取抓取汉字			
		    matcher=new Job51Matcher();
		    resume=matcher.parseWord(context);
			resume.setResumeURL(dbPath);
			System.out.println("dbPath:"+dbPath);
			break;
		case "htm":
			String htmText=UploadUtils.getHtmlText(is);//获得html文本内容
			htmText=htmText.substring(htmText.indexOf("<head>"));//获取的html文本内容需处理下
			String dbPaths=UploadUtils.saveHtml(htmText,Resume.FILE_UPLOAD_RESUME);//保存html到本地，返回数据库存储路径
			String htmContext=JsoupUtils.parseWord(htmText).substring(0,250);//从html获取抓取汉字
			if (htmContext.contains("智联招聘")) {
				matcher=new ZhiLianMatcher();
				resume=matcher.parseWord(htmContext);
				resume.setResumeURL(dbPaths);
			}else {
				//猎聘
				matcher=new LiePinMatcher();
				resume=matcher.parseWord(htmContext);
				resume.setResumeURL(dbPaths);
			}
			break;
		case "doc":
			System.out.println("Except : " + "doc");

			break;
		case "docx":
			System.out.println("Except : " + "docx");

			break;
		default:
			break;
		}
		System.out.println(resume.toString());
		return resume;		
	}

}
