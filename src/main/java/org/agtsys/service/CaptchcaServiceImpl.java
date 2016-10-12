package org.agtsys.service;

import java.io.OutputStream;
import org.agtsys.util.Captchca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaptchcaServiceImpl implements CaptchcaService {
	@Autowired
	private Captchca captchca; 
	@Override
	public String generateCaptchca(OutputStream out) throws Exception {
		return captchca.generateCaptchca(out);
	}
}
