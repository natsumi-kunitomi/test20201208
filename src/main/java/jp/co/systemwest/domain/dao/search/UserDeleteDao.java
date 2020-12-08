package jp.co.systemwest.domain.dao.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.systemwest.domain.exception.ApplicationException;
import jp.co.systemwest.domain.exception.SystemException;

/**
 * ユーザー情報の削除用DAO
 */

public class UserDeleteDao {
	/**
	 * ユーザー情報を削除
	 *
	 * @param registNo 登録番号
	 * @return
	 */
	public int deleteUser(int registNo) {

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true", "root", "kN284599");
			con.setAutoCommit(false);
			String sql = "DELETE FROM M_USER WHERE REGIST_NO = ?";

			ps = con.prepareStatement(sql);
			ps.setInt(1, registNo);
			int result = ps.executeUpdate();

			if (result > 1) {
				throw new ApplicationException();
			}
			con.commit();

		} catch (ClassNotFoundException cnfEx) {
					System.out.print(cnfEx.toString());
					throw new SystemException();

		} catch(SQLException sqlEx) {
			try {
				if (con != null) {
					con.rollback();
				}
			} catch (SQLException sqlEx2) {
				System.out.println(sqlEx2.toString());
			}
			System.out.println(sqlEx.toString());
			throw new SystemException();

		} finally {
			try{
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				throw new SystemException();
			}
		}
		return 0;
	}
}
