package com.example.junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("嵌套测试")
public class TestAStack {
	Stack<Object> stack;
	
	
	/**
	 * 测试参数化测试
	 *
	 * @param i
	 */
	@ParameterizedTest
	@DisplayName("参数化测试")
	@ValueSource(ints = {1, 2, 3, 4, 5})
	void testParameterized(int i) {
		System.out.println(i);
	}
	
	@ParameterizedTest
	@DisplayName("参数化测试")
	@MethodSource("stringStream")
	void testParameterized(String i) {
		System.out.println(i);
	}
	
	static Stream<String> stringStream() {
		return Stream.of("apple", "banana", "orange");
	}
	
	@Test
	@DisplayName("创建一个栈")
	void idInstantiatedWithNew() {
		new Stack<>();
		// 嵌套测试情况下，外层的Test不能驱动内层的BeforeEach/All之类的方法提前/之后运行
		assertNull(stack);
	}
	
	@Nested
	@DisplayName("")
	class WhenNew {
		@BeforeEach
		void createNewStack() {
			stack = new Stack<>();
		}
		
		/**
		 * 内层的可以驱动外层的
		 */
		
		@Test
		@DisplayName("是否是空")
		void isEmpty() {
			assertTrue(stack.isEmpty());
		}
		
		@Test
		@DisplayName("")
		void throwsExceptionWhenPeeked() {
			assertThrows(EmptyStackException.class, stack::peek);
		}
		
		@Nested
		@DisplayName("")
		class AfterPushing {
			String anElement = "an element";
			
			@BeforeEach
			void pushElement() {
				stack.push(anElement);
			}
			
			@Test
			@DisplayName("")
			void isNotEmpty() {
				assertFalse(stack.isEmpty());
			}
			
			@Test
			@DisplayName("")
			void retrunELementWhenPopped() {
				assertEquals(anElement, stack.pop());
				assertTrue(stack.isEmpty());
			}
			
			@Test
			@DisplayName("")
			void returnElementWhenPeeked() {
				assertEquals(anElement, stack.peek());
				assertFalse(stack.isEmpty());
			}
		}
	}
	
}
