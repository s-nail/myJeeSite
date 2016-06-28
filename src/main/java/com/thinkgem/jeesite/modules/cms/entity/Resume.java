package com.thinkgem.jeesite.modules.cms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Resume extends DataEntity<Resume> {

	private static final long serialVersionUID = 1L;
	private String name;
	private String sex;
	private String age;
	private String phone;
	private String email;
	private String degree;
	private String resumeNo;
	private String source;
	private String resumeURL;
	private String sendTime;
	private boolean unread;

	public static String FILE_UPLOAD_RESUME = "/resume/";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? "" : name.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? "" : sex.trim();
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age == null ? "" : age.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? "" : phone.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getResumeNo() {
		return resumeNo;
	}

	public void setResumeNo(String resumeNo) {
		this.resumeNo = resumeNo;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getResumeURL() {
		return resumeURL;
	}

	public void setResumeURL(String resumeURL) {
		this.resumeURL = resumeURL;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String string) {
		this.sendTime = string;
	}

	public boolean isUnread() {
		return unread;
	}

	public void setUnread(boolean unread) {
		this.unread = unread;
	}

	@Override
	public String toString() {
		return "name:" + name + "sex:" + sex + "age:" + age + "phone:" + phone
				+ "email:" + email + "degree:" + degree + "resumeNo:"
				+ resumeNo + "source:" + source + "resumeURL:" + resumeURL
				+ "unread:" + unread;
	}
}
