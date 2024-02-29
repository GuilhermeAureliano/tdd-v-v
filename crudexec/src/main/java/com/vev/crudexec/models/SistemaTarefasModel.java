package com.vev.crudexec.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SistemaTarefasModel {
    private List<TarefaModel> listaTarefas;

    public SistemaTarefasModel() {
        this.listaTarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        TarefaModel novaTarefa = new TarefaModel(titulo, descricao, dataVencimento, prioridade);
        listaTarefas.add(novaTarefa);
    }

    public void atualizarTarefa(TarefaModel tarefa, String novoTitulo, String novaDescricao, String novaDataVencimento, Prioridade novaPrioridade) {
        tarefa.setTitulo(novoTitulo);
        tarefa.setDescricao(novaDescricao);
        tarefa.setDataVencimento(novaDataVencimento);
        tarefa.setPrioridade(novaPrioridade);
    }

    public void excluirTarefa(TarefaModel tarefa) {
        if (tarefa != null) {
            listaTarefas.remove(tarefa);
        } else {
            throw new IllegalArgumentException("A tarefa fornecida é nula.");
        }
    }

    public List<TarefaModel> listarTarefas() {
        List<TarefaModel> copiaListaTarefas = new ArrayList<>(listaTarefas);
        Collections.sort(copiaListaTarefas);
        return copiaListaTarefas;
    }

    public void marcarPrioridade(TarefaModel tarefa, Prioridade prioridade) {
        tarefa.setPrioridade(prioridade);
    }
}
