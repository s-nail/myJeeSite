package com.thinkgem.jeesite.modules.cms.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.Date;







import org.apache.commons.io.IOUtils;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.cms.entity.Resume;
/*
 * Created by yuqiangjia on 2016/6/5.
 */
public class UploadUtils {
	public static void main(String[] args) throws InterruptedException, IOException {
		InputStream is = new FileInputStream("D:\\word\\智联招聘_李福临_中文_20160613_47337433.doc");
		
		String dbPath=upload(is, Resume.FILE_UPLOAD_RESUME);
		System.out.println("dbPath::"+dbPath);
		/*String ss=getHtmlText(is, "UTF-8");
		ss=ParseHtmlUtils.parseWord(ss);
		System.out.println(ss);*/
		
	}

	public static String upload(InputStream is, String savePath)
			throws IOException {
		String htmlName = new Date().getTime() + ".html";
		String dbPath = savePath + htmlName;
		String fullPath = Global.getConfig("basePath") + savePath + htmlName;
		OutputStream os = new FileOutputStream(fullPath);
		int bytesRead = 0;
		byte[] buffer = new byte[1024 * 5];
		while ((bytesRead = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		is.close();
		os.close();
		return dbPath;
	}

	public static String saveHtml(String strText, String savePath) throws Exception{
		String htmlName=new Date().getTime()+".html";
		String dbPath = savePath + htmlName;
		String fullPath = Global.getConfig("basePath") + savePath + htmlName;
		InputStream is=StringTOInputStream(strText, "UTF-8");
		OutputStream os=new FileOutputStream(fullPath);
		int bytesRead=0;
		byte[] buffer=new byte[1024*5];
		while ((bytesRead = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytesRead);
		}
		is.close();
		os.close();
		return dbPath;
		
	}
	public static Integer getFileByte(InputStream is) {
		Integer size = null;
		try {
			size = is.available();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return size;
	}

	public static InputStream StringTOInputStream(String in, String encoding)
			throws Exception {

		ByteArrayInputStream is = new ByteArrayInputStream(
				in.getBytes(encoding));
		return is;
	}
	
	public static String getHtmlText(InputStream is) throws IOException{
		return IOUtils.toString(is, "UTF-8");		
	}


	public static String getHtmlText(InputStream is, String strEncoding) {
		//InputStream textStream = null;
		BufferedInputStream buff = null;
		BufferedReader br = null;
		Reader r = null;
		try {
			//textStream = bp.getInputStream();
			buff = new BufferedInputStream(is);
			r = new InputStreamReader(buff, strEncoding);
			br = new BufferedReader(r);
			StringBuffer strHtml = new StringBuffer("");
			String strLine = null;
			while ((strLine = br.readLine()) != null) {
				strHtml.append(strLine + "\r\n");
			}
			br.close();
			r.close();
		    is.close();

			return strHtml.toString().replace("gb2312", "UTF-8");
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (buff != null)
					buff.close();
				if (is != null)
					is.close();
			} catch (Exception e) {
				System.out.println("解析mht失败");
			}
		}
		return null;
	}

}
