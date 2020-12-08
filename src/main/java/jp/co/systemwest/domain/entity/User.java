package jp.co.systemwest.domain.entity;

import java.io.Serializable;

public class User implements Serializable{
	/** シリアルバージョンID */
	private static final long serialVersionUID = 117574387650802127L;

	/** 登録番号 */
	Integer registNo;

	/** 姓(漢字) */
	String sei;
	/** 姓(カナ) */
	String seiKana;

	/** 名(漢字) */
	String mei;
	/** 名(カナ) */
	String meiKana;

	/** 郵便番号 */
	String postalCode;
	/** 住所１ */
	String address1;
	/** 住所２ */
	String address2;

	/** 電話番号 */
	String phoneNumber;
	/** メールアドレス */
	String mailAddress;

	/**
	 * 登録番号を取得します。
	 * @return 登録番号
	 */
	public Integer getRegistNo() {
	    return registNo;
	}
	/**
	 * 登録番号を設定します。
	 * @param registNo 登録番号
	 */
	public void setRegistNo(Integer registNo) {
	    this.registNo = registNo;
	}
	/**
	 * 姓(漢字)を取得します。
	 * @return 姓(漢字)
	 */
	public String getSei() {
	    return sei;
	}
	/**
	 * 姓(漢字)を設定します。
	 * @param sei 姓(漢字)
	 */
	public void setSei(String sei) {
	    this.sei = sei;
	}
	/**
	 * 姓(カナ)を取得します。
	 * @return 姓(カナ)
	 */
	public String getSeiKana() {
	    return seiKana;
	}
	/**
	 * 姓(カナ)を設定します。
	 * @param seiKana 姓(カナ)
	 */
	public void setSeiKana(String seiKana) {
	    this.seiKana = seiKana;
	}
	/**
	 * 名(漢字)を取得します。
	 * @return 名(漢字)
	 */
	public String getMei() {
	    return mei;
	}
	/**
	 * 名(漢字)を設定します。
	 * @param mei 名(漢字)
	 */
	public void setMei(String mei) {
	    this.mei = mei;
	}
	/**
	 * 名(カナ)を取得します。
	 * @return 名(カナ)
	 */
	public String getMeiKana() {
	    return meiKana;
	}
	/**
	 * 名(カナ)を設定します。
	 * @param meiKana 名(カナ)
	 */
	public void setMeiKana(String meiKana) {
	    this.meiKana = meiKana;
	}
	/**
	 * 郵便番号を取得します。
	 * @return 郵便番号
	 */
	public String getPostalCode() {
	    return postalCode;
	}
	/**
	 * 郵便番号を設定します。
	 * @param postalCode 郵便番号
	 */
	public void setPostalCode(String postalCode) {
	    this.postalCode = postalCode;
	}
	/**
	 * 住所１を取得します。
	 * @return 住所１
	 */
	public String getAddress1() {
	    return address1;
	}
	/**
	 * 住所１を設定します。
	 * @param address1 住所１
	 */
	public void setAddress1(String address1) {
	    this.address1 = address1;
	}
	/**
	 * 住所２を取得します。
	 * @return 住所２
	 */
	public String getAddress2() {
	    return address2;
	}
	/**
	 * 住所２を設定します。
	 * @param address2 住所２
	 */
	public void setAddress2(String address2) {
	    this.address2 = address2;
	}
	/**
	 * 電話番号を取得します。
	 * @return 電話番号
	 */
	public String getPhoneNumber() {
	    return phoneNumber;
	}
	/**
	 * 電話番号を設定します。
	 * @param phoneNumber 電話番号
	 */
	public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	}
	/**
	 * メールアドレスを取得します。
	 * @return メールアドレス
	 */
	public String getMailAddress() {
	    return mailAddress;
	}
	/**
	 * メールアドレスを設定します。
	 * @param mailAddress メールアドレス
	 */
	public void setMailAddress(String mailAddress) {
	    this.mailAddress = mailAddress;
	}
}
