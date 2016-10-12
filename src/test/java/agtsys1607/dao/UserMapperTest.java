package agtsys1607.dao;

import junit.framework.Assert;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {
	private UserMapper userMapper;
	@Before
	public void setUp() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-context.xml");
		userMapper=(UserMapper) ac.getBean("userMapper");
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testGetUserByUser() {
		User user = new User();
		user.setUsercode("admin");
		user.setUserpassword("1");
		Assert.assertNotNull(userMapper.getUserByUser(user));
	}

}
