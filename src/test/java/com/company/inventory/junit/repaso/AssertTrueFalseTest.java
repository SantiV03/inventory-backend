package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalseTest {

	@Test
	public void test1() {
		
		assertTrue(true);
		assertFalse(false);
	}
	
	@Test
	public void test2() {
		boolean expresoon1 = (4 == 4);
		
		assertTrue(expresoon1);
		
		boolean expresoon2 = (4 == 2);
		
		assertFalse(expresoon2);
	}
	
}
