package com.goodboy.picshop.dto;

public class PayDto extends BaseDto {
	public PayDto() {
	}

	public PayDto(int status, String info) {
		super(status, info);
	}

	public PayDto(StatusEnum statusEnum) {
		super(statusEnum);
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
