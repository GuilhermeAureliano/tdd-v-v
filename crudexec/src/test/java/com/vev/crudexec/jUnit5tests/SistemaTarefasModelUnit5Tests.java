package com.vev.crudexec.jUnit5tests;

import com.vev.crudexec.exceptions.DataVencimentoInvalidaException;
import com.vev.crudexec.exceptions.TarefaNaoEncontradaException;
import com.vev.crudexec.exceptions.TituloInvalidoException;
import com.vev.crudexec.models.Prioridade;
import com.vev.crudexec.models.SistemaTarefasModel;
import com.vev.crudexec.models.TarefaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SistemaTarefasModelUnit5Tests {

    private SistemaTarefasModel sistemaTarefas;

    @BeforeEach
    void setUp() {
        sistemaTarefas = new SistemaTarefasModel();
    }

    @Test
    @Order(1)
    @DisplayName("Teste de criação de tarefa com título nulo")
    void testCriarTarefaComTituloNulo() {
        assertThrows(TituloInvalidoException.class, () -> {
            sistemaTarefas.criarTarefa(null, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }

    @Test
    @Order(2)
    @DisplayName("Teste de criação de tarefa com data de vencimento inválida")
    void testCriarTarefaComDataVencimentoInvalida() {
        assertThrows(DataVencimentoInvalidaException.class, () -> {
            sistemaTarefas.criarTarefa("Titulo", "Descrição", "2022-01-01", Prioridade.ALTA);
        });
    }

    @Test
    @Order(3)
    @DisplayName("Teste de exclusão de tarefa inexistente")
    void testExcluirTarefaInexistente() {
        TarefaModel tarefa = new TarefaModel("Titulo", "Descrição", "2024-04-30", Prioridade.ALTA);
        assertThrows(TarefaNaoEncontradaException.class, () -> {
            sistemaTarefas.excluirTarefa(tarefa);
        });
    }

    @Test
    @Order(4)
    @DisplayName("Teste de criação de tarefa com título no limite de tamanho")
    void testCriarTarefaComTituloNoLimiteDeTamanho() {
        String titulo = "T".repeat(TarefaModel.MAX_TITULO_LENGTH);
        assertDoesNotThrow(() -> {
            sistemaTarefas.criarTarefa(titulo, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }

    @Test
    @Order(5)
    @DisplayName("Teste de criação de tarefa com título ultrapassando o limite de tamanho")
    void testCriarTarefaComTituloUltrapassandoLimiteDeTamanho() {
        String titulo = "T".repeat(TarefaModel.MAX_TITULO_LENGTH + 1);
        assertThrows(TituloInvalidoException.class, () -> {
            sistemaTarefas.criarTarefa(titulo, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }

    @Test
    @Order(6)
    @DisplayName("Teste de ordenação por prioridade")
    void testOrdenacaoPorPrioridade() {
        SistemaTarefasModel sistema = new SistemaTarefasModel();
        try {
            sistema.criarTarefa("Tarefa Baixa", "Descrição da Tarefa Baixa", "2024-04-10", Prioridade.BAIXA);
            sistema.criarTarefa("Tarefa Alta", "Descrição da Tarefa Alta", "2024-04-10", Prioridade.ALTA);
            sistema.criarTarefa("Tarefa Média", "Descrição da Tarefa Média", "2024-04-10", Prioridade.MEDIA);
        } catch (TituloInvalidoException | DataVencimentoInvalidaException e) {
            fail("Erro ao criar tarefa: " + e.getMessage());
        }

        List<TarefaModel> tarefasOrdenadas = sistema.listarTarefas();

        assertEquals(Prioridade.ALTA, tarefasOrdenadas.get(0).getPrioridade());
        assertEquals(Prioridade.MEDIA, tarefasOrdenadas.get(1).getPrioridade());
        assertEquals(Prioridade.BAIXA, tarefasOrdenadas.get(2).getPrioridade());
    }
}