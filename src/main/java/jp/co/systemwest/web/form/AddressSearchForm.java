package jp.co.systemwest.web.form;

import java.io.Serializable;

public class AddressSearchForm implements Serializable {

	private static final long serialVersionUID = 6846078304416664842L;

	private String sei;
	private String mei;
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
