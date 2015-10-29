package com.ccube.pod.domain;

public class User {

	private long uid;
	private String fullName;
	private String mobile;

	public User() {
	}

	public User(long uid, String fullName, String mobile) {
		super();
		this.uid = uid;
		this.fullName = fullName;
		this.mobile = mobile;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return fullName;
	}
}
