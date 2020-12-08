package jp.co.systemwest.domain.dao.edit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import jp.co.systemwest.domain.dto.SearchConditionDto;
import jp.co.systemwest.domain.entity.User;
import jp.co.systemwest.domain.exception.SystemException;

public class UserSearchDao {
	//指定した検索条件を元に、ユーザー一覧を取得
	//@param conditionDto 検索条件
	//@return 検索結果のユーザー一覧

	public List<User> searchUsers(SearchConditionDto conditionDto){
		Connection con = null;
		PreparedStatement ps = null;

		List<User> userList = new ArrayList<User>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/address_book?characterEncoding=UTF-8&serverTimezone=JST", "root", "kN284599");
			String sql = createSearchSql(conditionDto);

			ps = con.prepareStatement(sql);
			int paramIndex = 1;
			if (!StringUtils.isEmpty(conditionDto.getSei())) {
				ps.setString(paramIndex, conditionDto.getSei().concat("%"));
				paramIndex++;
			}
			if (!StringUtils.isEmpty(conditionDto.getMei())) {
				ps.setString(paramIndex, conditionDto.getMei().concat("%"));
				paramIndex++;
			}
			if (!StringUtils.isEmpty(conditionDto.getPhoneNumber())) {
				ps.setString(paramIndex, conditionDto.getPhoneNumber().concat("%"));
				paramIndex++;
			}
			ResultSet rs = ps.executeQuery();

			User user = null;
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

				userList.add(user);
			}
			rs.close();

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
		return userList;
	}

	//検索用SQLを構築
	//@param conditionDto 検索条件
	//@return 検索用SQL


	private String createSearchSql(SearchConditionDto conditionDto) {

		StringBuilder sbMain = new StringBuilder(1000);
		StringBuilder sbWhere = new StringBuilder(100);

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
			  .append("  M_USER usr ");

		if (!StringUtils.isEmpty(conditionDto.getSei())) {
			sbWhere.append("usr.SEI LIKE ? ");
		}
		if (!StringUtils.isEmpty(conditionDto.getMei())) {
			if (sbWhere.length() != 0) {
				sbWhere.append("AND ");
			}
			sbWhere.append("usr.MEI LIKE ? ");
		}
		if (!StringUtils.isEmpty(conditionDto.getPhoneNumber())) {
			if (sbWhere.length() != 0) {
				sbWhere.append("AND ");
			}
			sbWhere.append("usr.PHONE_NUMBER LIKE ? ");
		}
		if (sbWhere.length() != 0) {
			sbMain.append("WHERE ").append(sbWhere.toString());
		}

		sbMain.append("ORDER BY usr.REGIST_NO ");

		return sbMain.toString();
	}
}
