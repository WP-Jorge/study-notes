package cn.cslg.applysystem.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyAuthorizationException extends RuntimeException {
	
	private Integer code;
	
	public MyAuthorizationException(Integer code, String msg) {
		super(msg);
		this.code = code;
	}
	
}
