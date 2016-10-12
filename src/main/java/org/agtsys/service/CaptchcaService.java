package org.agtsys.service;

import java.io.OutputStream;

public interface CaptchcaService {
	String generateCaptchca(OutputStream out) throws Exception;
}
