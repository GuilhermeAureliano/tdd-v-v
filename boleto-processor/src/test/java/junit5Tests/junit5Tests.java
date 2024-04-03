package junit5Tests;

import com.example.models.Boleto;
import com.example.models.Fatura;
import com.example.models.ProcessadorBoletos;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class junit5Tests {
    private Fatura fatura;
    private ProcessadorBoletos processador;

    @BeforeEach
    void init() {
        fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        processador = new ProcessadorBoletos();
    }

    @Test
    @Order(1)
    @DisplayName("Teste Fatura Não Paga - Análise de Valores Limites")
    void testFaturaNaoPaga_AVL() {
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1499.00);
        processador.processa(Arrays.asList(boleto1), fatura);
        Assertions.assertFalse(fatura.isPago());
        Assertions.assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    @Order(2)
    @DisplayName("Teste Fatura Paga - Análise de Valores Limites")
    void testFaturaPaga_AVL() {
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1500.00);
        processador.processa(Arrays.asList(boleto1), fatura);
        Assertions.assertTrue(fatura.isPago());
        Assertions.assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    @Order(3)
    @DisplayName("Teste Fatura Valor Negativo - Partições de Equivalência")
    void testFaturaValorNegativo_PE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Fatura faturaNegativa = new Fatura("2024/03/19", -100.00, "Cliente 3");
            Boleto boleto1 = new Boleto("123", "2024/03/20", 100.00);
            processador.processa(Arrays.asList(boleto1), faturaNegativa);
        }, "Valor da fatura deve ser maior que zero");
    }

    @Test
    @Order(4)
    @DisplayName("Teste Fatura Valor Zero - Partições de Equivalência")
    void testFaturaValorZero_PE() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Fatura faturaZero = new Fatura("2024/03/19", 0.00, "Cliente 4");
            Boleto boleto1 = new Boleto("123", "2024/03/20", 0.00);
            processador.processa(Arrays.asList(boleto1), faturaZero);
        }, "Valor da fatura deve ser maior que zero");
    }

    @ParameterizedTest
    @ValueSource(doubles = {1500.00, 1600.00})
    @Order(5)
    @DisplayName("Teste Fatura Paga - Tabela de Decisão")
    void testFaturaPaga_TD(double valorBoleto) {
        Boleto boleto = new Boleto("123", "2024/03/20", valorBoleto);
        processador.processa(Arrays.asList(boleto), fatura);
        Assertions.assertTrue(fatura.isPago());
        Assertions.assertEquals(1, fatura.getPagamentos().size());
    }

    @RepeatedTest(5)
    @Order(6)
    @DisplayName("Teste Fatura Paga - Teste de Repetição")
    void testFaturaPaga_Repeated() {
        Boleto boleto = new Boleto("123", "2024/03/20", 1500.00);
        processador.processa(Arrays.asList(boleto), fatura);
        Assertions.assertTrue(fatura.isPago());
        Assertions.assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    @Order(7)
    @DisplayName("Teste Fatura Paga com Múltiplos Boletos")
    void testFaturaPaga_MultiplosBoletos() {
        Boleto boleto1 = new Boleto("123", "2024/03/20", 500.00);
        Boleto boleto2 = new Boleto("124", "2024/03/21", 500.00);
        Boleto boleto3 = new Boleto("125", "2024/03/22", 500.00);
        processador.processa(Arrays.asList(boleto1, boleto2, boleto3), fatura);
        Assertions.assertTrue(fatura.isPago());
        Assertions.assertEquals(3, fatura.getPagamentos().size());
    }

    @Test
    @Order(8)
    @DisplayName("Teste Fatura Não Paga com Múltiplos Boletos")
    void testFaturaNaoPaga_MultiplosBoletos() {
        Boleto boleto1 = new Boleto("123", "2024/03/20", 400.00);
        Boleto boleto2 = new Boleto("124", "2024/03/21", 400.00);
        Boleto boleto3 = new Boleto("125", "2024/03/22", 400.00);
        processador.processa(Arrays.asList(boleto1, boleto2, boleto3), fatura);
        Assertions.assertFalse(fatura.isPago());
        Assertions.assertEquals(3, fatura.getPagamentos().size());
    }

    @Test
    @Order(9)
    @DisplayName("Teste Boleto Valor Negativo")
    void testBoletoValorNegativo() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Boleto boletoNegativo = new Boleto("123", "2024/03/20", -100.00);
            processador.processa(Arrays.asList(boletoNegativo), fatura);
        }, "Valor do boleto deve ser maior que zero");
    }

    @Test
    @Order(10)
    @DisplayName("Teste Boleto Valor Zero")
    void testBoletoValorZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Boleto boletoZero = new Boleto("123", "2024/03/20", 0.00);
            processador.processa(Arrays.asList(boletoZero), fatura);
        }, "Valor do boleto deve ser maior que zero");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Iniciando a execução dos testes...");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Finalizando a execução dos testes...");
    }
}
