package com.thinkgem.jeesite.modules.cms.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * Created by yuqiangjia on 2016/6/8.
 */
public class JsoupUtils {	

	/*
	 * 使用jsoup提取email邮件HTML中汉字
	 */
	public static String parseEmail(String content) {
		StringBuffer sb=new StringBuffer();
		Document document = Jsoup.parse(content.toString());
		Elements div = document.getElementsByTag("span");
		for (Element e : div) {
			sb.append(e.text()) ;
		}
		return sb.toString();
	}
	
	/*
	 * 使用jsoup提取word转换HTML中汉字
	 */
	public static String parseWord(String content) throws InterruptedException {
		StringBuffer sb=new StringBuffer();
		Document document = Jsoup.parse(content.toString());
		Elements div = document.getElementsByTag("td");
		for (Element e : div) {
			sb.append(e.text()) ;
		}
		return sb.toString();
	}
}
