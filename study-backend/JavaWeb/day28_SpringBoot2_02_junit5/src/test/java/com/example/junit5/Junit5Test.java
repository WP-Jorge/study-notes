package com.example.junit5;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @DisplayName("测试名") 给测试取名
 * @BeforeEach 在每个测试方法执行前都会执行这个方法
 * @AfterEach 在每个测试方法结束后执行
 * @BeforeAll 所有测试方法执行前执行
 * @AfterAll 所有测试方法执行后执行
 * @Disabled 表示禁用当前测试
 * @Timeout 超时后报错
 */

@DisplayName("Junit5测试类")
public class Junit5Test {
	
	@Disabled
	@DisplayName("测试DisplayName注解")
	@Test
	void testDisplayName() {
		System.out.println(1);
	}
	
	@BeforeEach
	void testBeforeEach() {
		System.out.println("测试就要开始了");
	}
	
	@AfterEach
	void testAfterEach() {
		System.out.println("测试结束了");
	}
	
	// all方法必须添加static
	@BeforeAll
	static void testBeforeAll() {
		System.out.println("所有测试方法执行前执行");
	}
	
	@AfterAll
	static void testAfterAll() {
		System.out.println("所有测试方法执行后执行");
	}
	
	/**
	 * 规定超时时间超时单位，超出时间后抛出异常
	 * @throws InterruptedException
	 */
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	void testTimeout() throws InterruptedException {
		Thread.sleep(600);
	}
	
	/**
	 * 前面一个断言失败后后面代码不执行
	 */
	@DisplayName("测试简单断言")
	@Test
	void testSimpleAssertions() {
		// 验证值是否相等
		int i = 2 + 3;
		assertEquals(5, i, "业务逻辑计算失败");
		
		// 验证地址是否相等
		Object o = new Object();
		Object o1 = new Object();
		assertSame(o, o1, "两个对象不一样");
	}
	
	@Test
	@DisplayName("测试数组断言")
	void testArray() {
		assertArrayEquals(new int[] {1, 2}, new int[] {2, 1}, "两个数组不一样");
	}
	
	/**
	 * 全部断言成功才算成功
	 */
	@Test
	@DisplayName("测试组合断言")
	void testAll() {
		assertAll("test",
				() -> assertTrue(true && true, "结果不为true"),
				() -> assertEquals(1, 1, "结果不正确"));
	}
	
	/**
	 * 业务逻辑正确抛出异常
	 */
	@DisplayName("异常断言")
	@Test
	void testException() {
		// 断定业务逻辑一定出现异常
		assertThrows(ArithmeticException.class, () -> {int i = 10 / 0;}, "业务逻辑正确");
	}
	
	@Test
	@DisplayName("快速失败")
	void testFail() {
		int i = 0;
		if (i == 0) {
			fail("测试失败");
		}
	}
	
	/**
	 * 测试前置条件
	 */
	@Test
	@DisplayName("测试前置条件")
	void testAssumptions() {
		Assumptions.assumeTrue(true, "结果不是true");
		System.out.println("结果是true");
	}
}
