package com.vev.crudexec;

import com.vev.crudexec.exceptions.DataVencimentoInvalidaException;
import com.vev.crudexec.exceptions.TarefaNaoEncontradaException;
import com.vev.crudexec.exceptions.TituloInvalidoException;
import com.vev.crudexec.models.Prioridade;
import com.vev.crudexec.models.SistemaTarefasModel;
import com.vev.crudexec.models.TarefaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SistemaTarefasModelUnit5Tests{
	
	private SistemaTarefasModel sistemaTarefas;
	
	 @BeforeEach
	    void setUp() {
	        sistemaTarefas = new SistemaTarefasModel();
	    }

    @Test
    public void testCriarTarefaComTituloNulo() {
        assertThrows(TituloInvalidoException.class, () -> {
        	sistemaTarefas.criarTarefa(null, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }

    @Test
    public void testCriarTarefaComDataVencimentoInvalida() {
        assertThrows(DataVencimentoInvalidaException.class, () -> {
        	sistemaTarefas.criarTarefa("Titulo", "Descrição", "2022-01-01", Prioridade.ALTA);
        });
    }

    @Test
    public void testExcluirTarefaInexistente() {
        TarefaModel tarefa = new TarefaModel("Titulo", "Descrição", "2024-04-30", Prioridade.ALTA);
        assertThrows(TarefaNaoEncontradaException.class, () -> {
        	sistemaTarefas.excluirTarefa(tarefa);
        });
    }
    
    @Test
    public void testCriarTarefaComTituloNoLimiteDeTamanho() {
        String titulo = "T".repeat(TarefaModel.MAX_TITULO_LENGTH);
        assertDoesNotThrow(() -> {
        	sistemaTarefas.criarTarefa(titulo, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }
    
    @Test
    public void testCriarTarefaComTituloUltrapassandoLimiteDeTamanho() {
        String titulo = "T".repeat(TarefaModel.MAX_TITULO_LENGTH + 1);
        assertThrows(TituloInvalidoException.class, () -> {
        	sistemaTarefas.criarTarefa(titulo, "Descrição", "2024-04-30", Prioridade.ALTA);
        });
    }
    
    @Test
    public void testOrdenacaoPorPrioridade() {
        SistemaTarefasModel sistema = new SistemaTarefasModel();
        try {
            sistema.criarTarefa("Tarefa Baixa", "Descrição da Tarefa Baixa", "2024-04-10", Prioridade.BAIXA);
            sistema.criarTarefa("Tarefa Alta", "Descrição da Tarefa Alta", "2024-04-10", Prioridade.ALTA);
            sistema.criarTarefa("Tarefa Média", "Descrição da Tarefa Média", "2024-04-10", Prioridade.MEDIA);
        } catch (TituloInvalidoException | DataVencimentoInvalidaException e) {
        	fail("Erro ao criar tarefa: " + e.getMessage());
        }

        // Lista as tarefas (espera-se que a ordenação esteja correta)
        List<TarefaModel> tarefasOrdenadas = sistema.listarTarefas();
        
        assertEquals(Prioridade.ALTA, tarefasOrdenadas.get(0).getPrioridade());
        assertEquals(Prioridade.MEDIA, tarefasOrdenadas.get(1).getPrioridade());
        assertEquals(Prioridade.BAIXA, tarefasOrdenadas.get(2).getPrioridade());
    }


}
