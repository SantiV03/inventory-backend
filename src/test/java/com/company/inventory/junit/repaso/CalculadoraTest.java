package com.company.inventory.junit.repaso;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	Calculadora cal;

	@BeforeAll
	public static void primero() {
		System.out.println("primero");
		
	}
	
	@AfterAll
	public static void ultimo() {
		
		System.out.println("ultimo");
		
	}
	
	@AfterEach
	public void ultimoPorCadaPrueba() {
		
		System.out.println("ultimo por cada prueba");
		
	}
	
	@BeforeEach
	public void primeroPorCadaPrueba() {
		
		System.out.println("primero por cada prueba");
		cal = new Calculadora();
	}
	
	@Test
	@DisplayName("prueba de suma calculadora")
	public void sumarTest() {
		
		assertEquals(2, cal.sumar(1,1));
		assertFalse(cal.sumar(1,1) == 3);
	}
	
	
	@Test
	@Disabled(" desabilita esta prueba ")
	public void restartTest() {
		
		assertEquals(3, cal.restar(4,1));
		assertFalse(cal.restar(6,1) == 5);

}
	@Test
	public void multiplicartTest() {
		
		assertEquals(4, cal.multiplicar(4,1));
		assertFalse(cal.multiplicar(6,2) == 11);

}
	@Test
	public void dividirTest() {
		
		assertTrue(cal.dividir(4,2) == 2);
		assertFalse(cal.dividir(6,6) == 6);

}
}


























