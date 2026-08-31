package edu.psu.se411.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StackTest {

	@Test
	public void testPushAndPop() {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		assertEquals(3, stack.pop().intValue());
		assertEquals(2, stack.pop().intValue());
		assertEquals(1, stack.pop().intValue());
	}
	
}