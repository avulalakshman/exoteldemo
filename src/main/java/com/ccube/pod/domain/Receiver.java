package com.ccube.pod.domain;

public class Receiver {
	private long rid;
	private String fullName;
	private String mobile;

	public Receiver() {
	}
	public Receiver(long rid, String fullName, String mobile) {
		this.rid = rid;
		this.fullName = fullName;
		this.mobile = mobile;
	}

	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
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
