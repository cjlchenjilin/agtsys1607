package org.agtsys.exception;

public class AccessDenyException extends Exception {
	public AccessDenyException(){
		super();
	}
	
	public AccessDenyException(String message){
		super(message);
	}
}
