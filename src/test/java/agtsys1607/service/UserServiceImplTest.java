package agtsys1607.service;

import static org.junit.Assert.fail;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.agtsys.dao.UserMapper;
import org.agtsys.domain.User;
import org.agtsys.service.UserService;
import org.agtsys.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class UserServiceImplTest {
	@Tested
	UserServiceImpl userService;
	@Injectable
	UserMapper userMapper;

	@Before
	public void setUp() throws Exception {
		userService = new UserServiceImpl();
	}

	@Test
	public void test() {
		//录制
		new Expectations() {
			{
				userMapper.getUserByUser(withInstanceOf(User.class));
				times=2;
			}
		};
		//回放
		userService.getUserByUser(new User());
		userService.getUserByUser(new User());
		//验证
		new Verifications() {
			{
				userMapper.getUserByUser(withInstanceOf(User.class));
				times = 2;
			}
		};
	}
}
