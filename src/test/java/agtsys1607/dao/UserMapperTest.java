package agtsys1607.dao;

import java.util.Date;

import junit.framework.Assert;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.agtsys.util.MySqlPageTool;
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
	
	@Test
	public void testGetPageUserByNullUserAndNullPageTool() throws Exception {
		User user = new User();
		Assert.assertNotNull(userMapper.getPageUsersByUser(null, null));
	}
	
	@Test
	public void testGetPageUserByNullUserAndPageTool() throws Exception {
		User user = new User();
		Assert.assertEquals(5, userMapper.getPageUsersByUser(null, new MySqlPageTool(1, 5)).size());
	}
	
	@Test
	public void testGetPageUserByUserAndPageTool() throws Exception {
		User user = new User();
		user.setUsername("测试");
		Assert.assertEquals(5, userMapper.getPageUsersByUser(user, new MySqlPageTool(1, 5)).size());
	}
	
	@Test
	public void testInsertUserGetPK() throws Exception {
		User user = new User();
		user.setUsername("测试getkey");
		user.setUsercode("key6");
		user.setCreationtime(new Date());
		user.setCreatedby("admin");
		user.setRoleid(40L);
		user.setUserpassword("test1111");
		userMapper.insertUser(user);
		Assert.assertEquals(38, user.getId().intValue());
	}
}
