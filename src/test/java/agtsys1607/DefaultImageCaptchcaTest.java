package agtsys1607;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.agtsys.util.DefaultImageCaptchca;
import org.junit.Before;
import org.junit.Test;

public class DefaultImageCaptchcaTest {
	private DefaultImageCaptchca dic;
	
	@Before
	public void setUp() throws Exception {
		dic = new DefaultImageCaptchca();
	}

	@Test
	public void testGenerateCaptchca() throws Exception {
		FileOutputStream fos = new FileOutputStream("patcha_demo.png");
		String cs = dic.generateCaptchca(fos);
		System.out.println(cs);
	}

}
