package aopxml;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

public class BookProxy {
	@Pointcut(value = "execution(* aopxml.Book.buy(..))")
	public void pointdemo() {
	}
	
	@Before(value = "pointdemo()")
	public void before() {
		System.out.println("before.....");
	}
}
