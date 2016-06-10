package com.thinkgem.jeesite.modules.cms.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class Applicant extends DataEntity<Applicant> {

	private static final long serialVersionUID = 1L;

	private String name;
	private String sex;
	private String age;
	private String phone;
	private String email;
	private String degree;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
