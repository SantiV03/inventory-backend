package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTest {

	//si los arreglos o listas no son iguales la prueba falla
	
	@Test
	public void assertArrayTest() {
		
		String [] arre1 = {"aa", "bb"};
		String [] arre2 = {"aa", "bb"};
		String [] arre3 = {"aa", "bb"};
		String [] arre4 = {"aa", "cc", "zz", "jj"};
		
		assertArrayEquals(arre1, arre2);
		assertArrayEquals(arre1, arre3);
	}
	
}
