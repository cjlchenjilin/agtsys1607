package org.agtsys.controller;

import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.agtsys.service.CaptchcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("captchca/")
public class CaptchcaController {
	@Autowired
	private CaptchcaService captchcaService;
	
	@RequestMapping(value="get",method=RequestMethod.GET)
	public void getCaptchca(HttpServletResponse response) throws Exception{
		OutputStream out = response.getOutputStream();
		String captchca = captchcaService.generateCaptchca(out);
		out.flush();
		out.close();
	}
}
