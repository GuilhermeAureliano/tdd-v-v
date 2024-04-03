package com.vev.crudexec;

import com.vev.crudexec.models.Prioridade;
import com.vev.crudexec.models.SistemaTarefasModel;
import com.vev.crudexec.models.TarefaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SistemaTarefasModelBasicTests {

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

        @Test
        void marcarPrioridade() {
                TarefaModel tarefa = new TarefaModel("Tarefa 1", "Descrição 1", "2024-03-01", Prioridade.ALTA);
                sistemaTarefas.criarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(),
                                tarefa.getPrioridade());

                sistemaTarefas.marcarPrioridade(tarefa, Prioridade.MEDIA);

                assertEquals(Prioridade.MEDIA, tarefa.getPrioridade());
        }

        @Test
        void listarTarefas() {
                TarefaModel tarefa1 = new TarefaModel("Tarefa 1", "Descrição 1", "2024-03-01", Prioridade.ALTA);
                TarefaModel tarefa2 = new TarefaModel("Tarefa 2", "Descrição 2", "2024-03-02", Prioridade.MEDIA);
                TarefaModel tarefa3 = new TarefaModel("Tarefa 3", "Descrição 3", "2024-03-03", Prioridade.BAIXA);

                sistemaTarefas.criarTarefa(tarefa2.getTitulo(), tarefa2.getDescricao(), tarefa2.getDataVencimento(),
                                tarefa2.getPrioridade());
                sistemaTarefas.criarTarefa(tarefa1.getTitulo(), tarefa1.getDescricao(), tarefa1.getDataVencimento(),
                                tarefa1.getPrioridade());
                sistemaTarefas.criarTarefa(tarefa3.getTitulo(), tarefa3.getDescricao(), tarefa3.getDataVencimento(),
                                tarefa3.getPrioridade());

                List<TarefaModel> listaTarefas = sistemaTarefas.listarTarefas();

                assertAll("listaTarefas",
                                () -> assertEquals(tarefa1.getTitulo(), listaTarefas.get(0).getTitulo()),
                                () -> assertEquals(tarefa1.getDescricao(), listaTarefas.get(0).getDescricao()),
                                () -> assertEquals(tarefa1.getDataVencimento(),
                                                listaTarefas.get(0).getDataVencimento()),
                                () -> assertEquals(tarefa1.getPrioridade(), listaTarefas.get(0).getPrioridade()),

                                () -> assertEquals(tarefa2.getTitulo(), listaTarefas.get(1).getTitulo()),
                                () -> assertEquals(tarefa2.getDescricao(), listaTarefas.get(1).getDescricao()),
                                () -> assertEquals(tarefa2.getDataVencimento(),
                                                listaTarefas.get(1).getDataVencimento()),
                                () -> assertEquals(tarefa2.getPrioridade(), listaTarefas.get(1).getPrioridade()),

                                () -> assertEquals(tarefa3.getTitulo(), listaTarefas.get(2).getTitulo()),
                                () -> assertEquals(tarefa3.getDescricao(), listaTarefas.get(2).getDescricao()),
                                () -> assertEquals(tarefa3.getDataVencimento(),
                                                listaTarefas.get(2).getDataVencimento()),
                                () -> assertEquals(tarefa3.getPrioridade(), listaTarefas.get(2).getPrioridade()));
        }

        @Test
        void atualizarTarefa() {
                TarefaModel tarefa = new TarefaModel("Tarefa 1", "Descrição 1", "2024-03-01", Prioridade.ALTA);
                sistemaTarefas.criarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataVencimento(),
                                tarefa.getPrioridade());

                sistemaTarefas.atualizarTarefa(tarefa, "Tarefa Atualizada", "Nova Descrição", "2024-03-02",
                                Prioridade.MEDIA);

                assertEquals("Tarefa Atualizada", tarefa.getTitulo());
                assertEquals("Nova Descrição", tarefa.getDescricao());
                assertEquals("2024-03-02", tarefa.getDataVencimento());
                assertEquals(Prioridade.MEDIA, tarefa.getPrioridade());
        }

}
