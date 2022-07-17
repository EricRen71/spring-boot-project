package net.springboot2.login;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
class LoginApplicationTests {

	@Resource
	private DataSource dataSource;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getConnection() throws SQLException {
		System.out.println(dataSource.getConnection());
	}

}
