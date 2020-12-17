package jp.co.systemwest.domain.dao.edit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.systemwest.domain.entity.Sign;
import jp.co.systemwest.domain.exception.SystemException;

public class SignInInsertDao {

	/**
	 * パスワードの新規登録
	 * @param ユーザー情報
	 */
	public void insertUser(Sign sign) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=UTF-8&serverTimezone=JST", "root", "kN284599");

			String sql = createInsertSql();
			ps = con.prepareStatement(sql);
			ps.setString(1, sign.getUser());
			ps.setString(2, sign.getPassword());

		} catch(ClassNotFoundException cnfEx) {
			System.out.print(cnfEx.toString());
			throw new SystemException();

		} catch(SQLException sqlEx) {
			System.out.println(sqlEx.toString());
			throw new SystemException();

		} finally {
			try{
				if (rs != null) {
					rs.close();
				}
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
	}

	/**
	 * INSERT用SQLを返却
	 *
	 * @return INSERT用SQL
	 */
	private String createInsertSql() {

		StringBuilder sbMain = new StringBuilder(1000);

		sbMain.append("INSERT INTO M_SIGN")
			  .append("(USER,PASSWORD)")
			  .append("VALUES")
			  .append("(?,?)");

		return sbMain.toString();
	}
}
