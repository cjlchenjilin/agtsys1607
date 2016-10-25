package org.agtsys.dao;

import java.util.List;

import org.agtsys.domain.CheckFunction;
import org.agtsys.domain.Function;
import org.agtsys.domain.Role;

public interface FunctionMapper {
	public List<Function> getFunctionByRole(Role role) throws Exception;
	public List<CheckFunction> getAllFunction()throws Exception;
}