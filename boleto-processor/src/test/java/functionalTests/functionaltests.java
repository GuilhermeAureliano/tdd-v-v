package functionalTests;

import com.example.models.Boleto;
import com.example.models.Fatura;
import com.example.models.ProcessadorBoletos;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class functionaltests {


    // Testes usando a ténica de Análise de Valores Limites
    @Test
    void testFaturaNaoPaga_AVL() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1499.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertFalse(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaPagaValorExato_AVL() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1500.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertTrue(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaPaga_AVL() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1501.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertTrue(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }


    // Testes usando a técnica de Partições de Equivalência
    @Test
    void testFaturaPagaValorIgual_PE() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1500.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertTrue(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaNaoPagaValorMenor_PE() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 2");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1400.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertFalse(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaPagaValorMaior_PE() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 3");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 1600.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1), fatura);

        assertTrue(fatura.isPago());
        assertEquals(1, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaValorZero_PE() {
        assertThrows(IllegalArgumentException.class, () -> {
            Fatura fatura = new Fatura("2024/03/19", 0.00, "Cliente 4");
            Boleto boleto1 = new Boleto("123", "2024/03/20", 0.00);

            ProcessadorBoletos processador = new ProcessadorBoletos();
            processador.processa(Arrays.asList(boleto1), fatura);
        }, "Valor da fatura deve ser maior que zero");
    }

    // Testes usando a técnica Tabela de Decisão
    @Test
    void testFaturaPaga_TD() {
        Fatura fatura = new Fatura("2024/03/19", 1500.00, "Cliente 1");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 500.00);
        Boleto boleto2 = new Boleto("456", "2024/03/21", 400.00);
        Boleto boleto3 = new Boleto("789", "2024/03/22", 600.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1, boleto2, boleto3), fatura);

        assertTrue(fatura.isPago());
        assertEquals(3, fatura.getPagamentos().size());
    }

    @Test
    void testFaturaNaoPaga_TD() {
        Fatura fatura = new Fatura("2024/03/19", 2000.00, "Cliente 2");
        Boleto boleto1 = new Boleto("123", "2024/03/20", 500.00);
        Boleto boleto2 = new Boleto("456", "2024/03/21", 400.00);
        Boleto boleto3 = new Boleto("789", "2024/03/22", 600.00);

        ProcessadorBoletos processador = new ProcessadorBoletos();
        processador.processa(Arrays.asList(boleto1, boleto2, boleto3), fatura);

        assertFalse(fatura.isPago());
        assertEquals(3, fatura.getPagamentos().size());
    }
}
