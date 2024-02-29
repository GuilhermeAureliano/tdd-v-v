package com.vev.crudexec;

import com.vev.crudexec.models.Prioridade;
import com.vev.crudexec.models.SistemaTarefasModel;
import com.vev.crudexec.models.TarefaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTarefasModelTest {

    private SistemaTarefasModel sistemaTarefas;

    @BeforeEach
    void setUp() {
        sistemaTarefas = new SistemaTarefasModel();
    }

    @Test
    void criarTarefa() {
        sistemaTarefas.criarTarefa("Tarefa 1", "Descrição 1", "2024-03-01", Prioridade.ALTA);
        List<TarefaModel> listaTarefas = sistemaTarefas.listarTarefas();

        assertEquals(1, listaTarefas.size());
    }

    @Test
    void excluirTarefa() {
        TarefaModel tarefa = new TarefaModel("Tarefa 1", "Descrição 1", "2024-03-01", Prioridade.ALTA);
        sistemaTarefas.criarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(),
                tarefa.getPrioridade());

        sistemaTarefas.excluirTarefa(tarefa);

        List<TarefaModel> listaTarefas = sistemaTarefas.listarTarefas();
        assertFalse(listaTarefas.contains(tarefa), "A lista de tarefas não deve conter a tarefa excluída.");
    }
}
