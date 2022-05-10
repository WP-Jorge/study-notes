package aopano;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class PersonProxy {
	@Pointcut(value = "execution(* aopano.User.add(..))")
	public void pointdemo() {
	}
	
	@Before(value = "pointdemo()")
	public void before() {
		System.out.println("Person before.....");
	}
}
