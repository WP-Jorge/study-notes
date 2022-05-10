package aopano;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

// 增强的类
@Component
@Aspect // 生成一个代理对象
@Order(2)
public class UserProxy {
	static int i = 0;
	
	// 相同切入点抽取
	@Pointcut(value = "execution(* aopano.User.add(..))")
	public void pointdemo() {
		
	}
	
	// 前置通知
	// @Before 注解作为前置通知
	@Before(value = "pointdemo()")
	public void before() {
		System.out.println(++i + " before.....");
	}
	
	// 返回值之后执行
	@AfterReturning(value = "pointdemo()")
	public void afterReturning() {
		System.out.println(++i + " afterReturning.....");
	}
	
	@After(value = "pointdemo()")
	public void after() {
		System.out.println(++i + " after.....");
	}
	
	@AfterThrowing(value = "pointdemo()")
	public void afterThrowing() {
		System.out.println(++i + " afterThrowing.....");
	}
	
	// 环绕通知，在方法前后都会执行
	@Around(value = "pointdemo()")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println(++i + " 环绕之前.....");
		
		// 被增强的方法
		proceedingJoinPoint.proceed();
		
		System.out.println(++i + " 环绕之后.....");
		
	}
}
