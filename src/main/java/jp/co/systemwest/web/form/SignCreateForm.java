package jp.co.systemwest.web.form;

import java.io.Serializable;

public class SignCreateForm implements Serializable{
	/** ユーザー */
	String user;

	/** パスワード */
	String password;

	/** 確認用パスワード */
	String password2;

	/**
	 * ユーザー取得
	 * @return ユーザー
	 */
	public String getUser() {
		return user;
	}

	/**
	 * ユーザー設定
	 * @param user ユーザー
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/** パスワード取得
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワード設定
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/** 確認用パスワード取得
	 * @return パスワード
	 */
	public String getPassword2() {
		return password2;
	}

	/**
	 * パスワード設定
	 * 確認用@param password パスワード
	 */
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
