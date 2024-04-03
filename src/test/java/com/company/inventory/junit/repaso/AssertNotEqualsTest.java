package com.company.inventory.junit.repaso;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTest {
	
	//si los numeros, o las respuestas de algun metodo, son iguales el metodo falla

	
	@Test
	public void miTest() {
		 assertNotEquals(3, 1);  //(elemento esperado (1),elemento actual (1))
	}

}
