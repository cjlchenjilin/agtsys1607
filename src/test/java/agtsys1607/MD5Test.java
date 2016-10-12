package agtsys1607;

import static org.junit.Assert.*;

import org.agtsys.util.MD5;
import org.junit.Before;
import org.junit.Test;

public class MD5Test {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String md5 = MD5.md5encode("123456");
		assertEquals("e10adc3949ba59abbe56e057f20f883e", md5);
	}

}
