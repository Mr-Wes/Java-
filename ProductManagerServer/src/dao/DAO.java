package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
/**
 * 数据库连接类，主要负责数据库的连接，关闭操作
 * @author Administrator
 *
 */
public class DAO {

	private static String driver;
	private static String url;
	private static String user_name;
	private static String password;
	Connection conn;
	PreparedStatement state;
	ResultSet resultSet;
	
	static {
		//1：加载Properties，获得相应参数
		Properties properties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream("./src/model/SQL.properties"));
			properties.load(in);
			driver = properties.getProperty("driver", "");
			url = properties.getProperty("url", "");
			user_name = properties.getProperty("user_name", "");
			password = properties.getProperty("password", "");
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2：加载驱动程序
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user_name, password);
	}
	public void closeAll() {
		try {
			if(conn!=null) {
				conn.close();
			}
			if(state!=null) {
				state.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
		}catch (SQLException e) {
				e.printStackTrace();
		}

	}
}
