package jp.co.systemwest.domain.dao.edit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.systemwest.domain.entity.User;
import jp.co.systemwest.domain.exception.SystemException;

public class UserInsertDao {
	/**
	 * ユーザーの新規登録
	 * 登録番号は新規採番を行い登録する
	 *
	 * @param usr 登録対象のユーザー情報
	 */
	public void insertUser(User usr) {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=UTF-8&serverTimezone=JST", "root", "kN284599");

			int registNo = getNewRegistNo(con);

			String sql = createInsertSql();
			ps = con.prepareStatement(sql);
			ps.setInt(1, registNo);
			ps.setString(2, usr.getSei());
			ps.setString(3, usr.getMei());
			ps.setString(4, usr.getSeiKana());
			ps.setString(5, usr.getMeiKana());
			ps.setString(6, usr.getPostalCode());
			ps.setString(7, usr.getAddress1());
			ps.setString(8, usr.getAddress2());
			ps.setString(9, usr.getPhoneNumber());
			ps.setString(10, usr.getMailAddress());

			ps.executeUpdate();

		} catch (ClassNotFoundException cnfEx) {
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
	 * 現在登録されている最大番号の＋１をした値を取得
	 *
	 * @param con DB接続情報
	 * @return 最大値＋１
	 */
	private int getNewRegistNo(Connection con) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = -1;
		try {
			ps = con.prepareStatement("SELECT MAX(REGIST_NO) + 1 FROM M_USER");
			rs = ps.executeQuery();

			rs.next();
			result = rs.getInt(1);
			rs.close();

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
			} catch (SQLException sqlEx) {
				throw new SystemException();
			}
		}

		return result;
	}

	/**
	 * INSERT用SQLを返却
	 *
	 * @return INSERT用SQL
	 */
	private String createInsertSql() {

		StringBuilder sbMain = new StringBuilder(1000);

		sbMain.append("INSERT INTO M_USER ")
			  .append("(REGIST_NO, SEI, MEI, SEI_KANA, MEI_KANA, POSTAL_CODE, ADDRESS_1, ADDRESS_2, PHONE_NUMBER, MAIL_ADDRESS) ")
			  .append("VALUES ")
			  .append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		return sbMain.toString();
	}
}
