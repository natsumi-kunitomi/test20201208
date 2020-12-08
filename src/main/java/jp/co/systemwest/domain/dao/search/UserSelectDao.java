package jp.co.systemwest.domain.dao.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.systemwest.domain.entity.User;
import jp.co.systemwest.domain.exception.SystemException;



public class UserSelectDao {
	/**
	 * 検索対象の登録番号
	 *
	 * @param registNo 登録番号
	 * @return ユーザー情報
	 */
	public User getUser(int registNo) {

		Connection con = null;
		PreparedStatement ps = null;
		User user = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true", "root", "kN284599");
			String sql = createSearchSql();

			ps = con.prepareStatement(sql);
			ps.setInt(1, registNo);
			ResultSet rs = ps.executeQuery();

			// １件しか取得できない想定
			while (rs.next()) {
				user = new User();
				user.setRegistNo(rs.getInt("REGIST_NO"));
				user.setSei(rs.getString("SEI"));
				user.setSeiKana(rs.getString("SEI_KANA"));
				user.setMei(rs.getString("MEI"));
				user.setMeiKana(rs.getString("MEI_KANA"));
				user.setPostalCode(rs.getString("POSTAL_CODE"));
				user.setAddress1(rs.getString("ADDRESS_1"));
				user.setAddress2(rs.getString("ADDRESS_2"));
				user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				user.setMailAddress(rs.getString("MAIL_ADDRESS"));
			}

		} catch (ClassNotFoundException cnfEx) {
			System.out.print(cnfEx.toString());
			throw new SystemException();

		} catch(SQLException sqlEx) {
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
		return user;
	}

	/**
	 * ユーザー検索用SQLを作成
	 *
	 * @return SELECT用SQL
	 */
	private String createSearchSql() {

		StringBuilder sbMain = new StringBuilder(1000);

		sbMain.append("SELECT ")
			  .append("  usr.REGIST_NO, ")
			  .append("  usr.SEI, ")
			  .append("  usr.MEI, ")
			  .append("  usr.SEI_KANA, ")
			  .append("  usr.MEI_KANA, ")
			  .append("  usr.POSTAL_CODE, ")
			  .append("  usr.ADDRESS_1, ")
			  .append("  usr.ADDRESS_2, ")
			  .append("  usr.PHONE_NUMBER, ")
			  .append("  usr.MAIL_ADDRESS ")
			  .append("FROM ")
			  .append("  M_USER usr ")
			  .append("WHERE ")
			  .append("usr.REGIST_NO = ?");

		return sbMain.toString();
	}
}
