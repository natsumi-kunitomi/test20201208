package jp.co.systemwest.domain.service;

import jp.co.systemwest.domain.dao.search.UserDeleteDao;

/**
 * ユーザー削除Service
 */

public class UserDeleteService {
	/**
	 * ユーザー情報の削除
	 *
	 * @param registNo 削除対象の登録番号
	 * @return
	 */
	public int deleteUser(int registNo) {

		UserDeleteDao dao = new UserDeleteDao();
		return dao.deleteUser(registNo);
	}
}
