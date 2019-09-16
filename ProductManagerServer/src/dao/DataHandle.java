package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 负责处理增删改查指令
 * @author Administrator
 *
 */
public class DataHandle extends DAO implements DataInterface{

	private static class Holder{
		private static final DataHandle INSTANCE = new DataHandle();
	}	
	private DataHandle() {}	
	public static final DataHandle getInstance() {
		return Holder.INSTANCE;
	}
	
	/**
	 * 返回：0-连接失败；-1-用户不存在；-2-登录密码错误；其他-登录成功
	 */
	@Override
	public int testLogin(String user_name, String user_password) {
		try {
			PreparedStatement ps = null;
			ResultSet result = null;
			Connection conn=getConnection();
			String sql="select * from hjdb.user_table where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			result = ps.executeQuery();
			if(result.next()) {
				sql="select * from hjdb.user_table where user_name=? and user_password=?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_name);
				ps.setString(2, user_password);
				result = ps.executeQuery();
				if(result.next()) {
					int position=result.getInt("user_position");
					return position;
				}else {
					return -2;
				}
			}else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int registe(String user_name, String user_password, int position) {
		try {
			PreparedStatement ps = null;
			ResultSet result = null;
			Connection conn=getConnection();
			String sql="select * from hjdb.user_table where user_name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			result = ps.executeQuery();
			if(result.next()) {
				return 1;//用户已存在
			}else {
				sql="insert into hjdb.user_table(user_name,user_password,user_position) values(?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_name);
				ps.setString(2, user_password);
				if(position==0) {
					position=1;
				}else if(position==1){
					position=2;
				}else if(position==2) {
					position=5;
				}else {
					position=7;
				}
				ps.setInt(3, position);
				ps.execute();
				return 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
