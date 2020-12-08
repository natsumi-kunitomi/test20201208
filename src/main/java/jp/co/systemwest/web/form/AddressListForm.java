package jp.co.systemwest.web.form;

import java.io.Serializable;

public class AddressListForm implements Serializable{

	private static final long serialVersionUID = -9171155372967970142L;
	String registNo;

	//registNoを取得します。
	public String getRegistNo() {
		return registNo;
	}

	//registNoを設定します。
	public void setRegistNo(String registNo) {
		this.registNo = registNo;
	}

}
