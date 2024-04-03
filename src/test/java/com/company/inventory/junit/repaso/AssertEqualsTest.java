package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTest {
	
	//si los numeros, o las respuestas de algun metodo, no son iguales el metodo falla
	
	@Test
	public void miTest() {
		 assertEquals(1, 1);  //(elemento esperado (1),elemento actual (1))
	}

}
