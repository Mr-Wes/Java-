package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

class DataHandleTest {

	@Test
	void test() {
		Class.forName("driver=com.mysql.cj.jdbc.Driver");
		DriverManager.getConnection("jdbc:mysql://b-26vgtfryrnug8z.bch.rds.bj.baidubce.com:3306/hjpm?useSSL=false&serverTimezone=UTC", "hj", "000000")
		fail("Not yet implemented");
	}

}
