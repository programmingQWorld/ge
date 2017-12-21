package com.goodboy.picshop.dto;

/**
 * dto父类  封装操作基类
 */
public class BaseDto {
	private int status;     // 标识
	private String info;    // 标识信息

	public BaseDto() {
	}

	public BaseDto(int status, String info) {
		this.status = status;
		this.info = info;
	}

	public BaseDto(StatusEnum statusEnum) {
		this.status = statusEnum.getStatus();
		this.info = statusEnum.getInfo();
	}

	@Override
	public String toString() {
		return "BaseDto{" +
				"status=" + status +
				", info='" + info + '\'' +
				'}';
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}