package jp.co.systemwest.web.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



public class UserRegistForm implements Serializable{

	private static final long serialVersionUID = -4286665529431795626L;

	String registNo;

	@NotEmpty(message="姓が入力されていません。")
	String sei;

	@Pattern(regexp = "^[ァ-ヶー]*$", message="姓のフリガナはカタカナで入力してください")
	String seiKana;

	@NotEmpty(message="名が入力されていません。")
	String mei;

	@Pattern(regexp = "^[ァ-ヶー]*$", message="名のフリガナはカタカナで入力してください")
	String meiKana;

	@Pattern(regexp = "[0-9]*$",message="郵便番号は半角英数字のみで入力してください。")
	String postalCode;

	String address1;
	String address2;

	@Pattern(regexp = "[0-9]*$",message="電話番号は半角英数字のみで入力してください。")
	String phoneNumber;

	String mailAddress;

	public String getRegistNo() {
		return registNo;
	}

	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

	public String getSei() {
		return sei;
	}

	public void setSei(String sei) {
		this.sei = sei;
	}

	public String getSeiKana() {
		return seiKana;
	}

	public void setSeiKana(String seiKana) {
		this.seiKana = seiKana;
	}

	public String getMei() {
		return mei;
	}

	public void setMei(String mei) {
		this.mei = mei;
	}

	public String getMeiKana() {
		return meiKana;
	}

	public void setMeiKana(String meiKana) {
		this.meiKana = meiKana;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
}
