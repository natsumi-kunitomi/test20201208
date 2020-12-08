package jp.co.systemwest.domain.dao.edit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.systemwest.domain.entity.User;
import jp.co.systemwest.domain.exception.SystemException;


	/**
	 * ユーザー情報の更新用DAO
	 */
	public class UserUpdateDao{

		/**
		 * ユーザーの更新を実行
		 *
		 * @param usr 更新するユーザー情報
		 */
		public void updateUser(User usr) {

			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true", "root", "kN284599");

				String sql = createUpdateSql();
				ps = con.prepareStatement(sql);

				ps.setString(1, usr.getSei());
				ps.setString(2, usr.getMei());
				ps.setString(3, usr.getSeiKana());
				ps.setString(4, usr.getMeiKana());
				ps.setString(5, usr.getPostalCode());
				ps.setString(6, usr.getAddress1());
				ps.setString(7, usr.getAddress2());
				ps.setString(8, usr.getPhoneNumber());
				ps.setString(9, usr.getMailAddress());
				ps.setInt(10, usr.getRegistNo().intValue());

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
		 * 更新用のSQLを構築
		 *
		 * @return UPDATE用SQL
		 */
		private String createUpdateSql() {

			StringBuilder sbMain = new StringBuilder(1000);

			sbMain.append("UPDATE M_USER ")
				  .append("SET ")
				  .append("  SEI = ?, ")
				  .append("  MEI = ?, ")
				  .append("  SEI_KANA = ?, ")
				  .append("  MEI_KANA = ?, ")
				  .append("  POSTAL_CODE = ?, ")
				  .append("  ADDRESS_1 = ?, ")
				  .append("  ADDRESS_2 = ?, ")
				  .append("  PHONE_NUMBER = ?, ")
				  .append("  MAIL_ADDRESS = ? ")
				  .append("WHERE ")
				  .append("  REGIST_NO = ? ");

			return sbMain.toString();
		}
	}
