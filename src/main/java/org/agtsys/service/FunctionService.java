package org.agtsys.service;

import java.util.List;
import org.agtsys.domain.Function;
import org.agtsys.domain.Role;

public interface FunctionService {
	List<Function> getFunctionByRole(Role role) throws Exception;
}
