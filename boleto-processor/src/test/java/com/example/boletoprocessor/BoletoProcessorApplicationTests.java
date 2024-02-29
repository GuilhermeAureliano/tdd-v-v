package com.example.boletoprocessor;

import com.example.models.Boleto;
import com.example.models.Fatura;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoletoProcessorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testFaturaPaga() {
		Fatura fatura = new Fatura("2024-02-29", 1500, "João Da Silva");
		Boleto boleto1 = new Boleto("123", "2024-02-28", 500);
		Boleto boleto2 = new Boleto("651", "2024-02-28", 400);
		Boleto boleto3 = new Boleto("321", "2024-02-28", 600);

		fatura.addBoleto(boleto1);
		fatura.addBoleto(boleto2);
		fatura.addBoleto(boleto3);

		fatura.pagar();

		assertTrue(fatura.isPago());
		assertEquals(3, fatura.getPagamentos().size());
		assertEquals(500, fatura.getPagamentos().get(0).getValorPago());
		assertEquals(400, fatura.getPagamentos().get(1).getValorPago());
		assertEquals(600, fatura.getPagamentos().get(2).getValorPago());
	}

	@Test
	void testFaturaNaoPaga() {
		Fatura fatura = new Fatura("2024-02-29", 1500, "João Da Silva");
		Boleto boleto1 = new Boleto("123", "2024-02-28", 500);
		Boleto boleto2 = new Boleto("651", "2024-02-28", 400);

		fatura.addBoleto(boleto1);
		fatura.addBoleto(boleto2);

		fatura.pagar();

		assertFalse(fatura.isPago());
	}

}
