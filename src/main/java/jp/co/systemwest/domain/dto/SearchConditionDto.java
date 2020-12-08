package jp.co.systemwest.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;


//入力された条件をセッションに格納するためのDTO

public class SearchConditionDto implements Serializable{

	private static final long serialVersionUID = -1305205455148558239L;

	private String sei;

	private String mei;

	@Pattern(regexp = "(^[0-9]{2,4}-[0-9]{2,4}-[0-9]{3,4}$)*$",message="半角英数字と-(ハイフン)で電話番号を入力してください。")
	private String phoneNumber;

	public String getSei() {
		return sei;
	}

	public void setSei(String sei) {
		this.sei = sei;
	}

	public String getMei() {
		return mei;
	}

	public void setMei(String mei) {
		this.mei = mei;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
