package cn.cslg.applysystem.exception;

import org.springframework.security.core.AuthenticationException;

public class MyPasswordErrorException extends AuthenticationException {
	
	public MyPasswordErrorException(String msg) {
		super(msg);
	}
}
