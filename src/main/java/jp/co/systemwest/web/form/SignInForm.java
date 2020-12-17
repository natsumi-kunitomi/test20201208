package jp.co.systemwest.web.form;

import java.io.Serializable;

public class SignInForm implements Serializable {

	/** ユーザー */
	String user;

	/** パスワード */
	String password;

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

}
