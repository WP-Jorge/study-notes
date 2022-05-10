package exception;

// 年龄出现异常
public class AgeException extends MyUserException {
	public AgeException() {
		super();
	}
	
	public AgeException(String message) {
		super(message);
	}
}
