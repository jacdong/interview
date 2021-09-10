package com.jacdong.interview.common.id;

public enum BussinessType {
	USER("USER", "用户模块"),LOGIN("LOGIN","登录模块");

	private final String code;
	private final String info;

	BussinessType(String code, String info) {
		this.code = code;
		this.info = info;
	}

	public String getCode() {
		return code;
	}

	public String getInfo() {
		return info;
	}
}
