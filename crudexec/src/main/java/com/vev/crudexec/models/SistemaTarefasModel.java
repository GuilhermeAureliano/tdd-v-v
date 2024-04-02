package com.vev.crudexec.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.vev.crudexec.exceptions.TituloInvalidoException;
import com.vev.crudexec.exceptions.DataVencimentoInvalidaException;
import com.vev.crudexec.exceptions.TarefaNaoEncontradaException;

public class SistemaTarefasModel {
    private List<TarefaModel> listaTarefas;

    public SistemaTarefasModel() {
        this.listaTarefas = new ArrayList<>();
    }

    public void criarTarefa(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        if (titulo != null && titulo.length() <= TarefaModel.MAX_TITULO_LENGTH) {
            if (validarDataVencimento(dataVencimento)) {
                TarefaModel novaTarefa = new TarefaModel(titulo, descricao, dataVencimento, prioridade);
                listaTarefas.add(novaTarefa);
            } else {
                throw new DataVencimentoInvalidaException("A data de vencimento é inválida.");
            }
        } else {
            throw new TituloInvalidoException("O título é inválido ou excede o tamanho máximo permitido.");
        }
    }

    public void atualizarTarefa(TarefaModel tarefa, String novoTitulo, String novaDescricao, String novaDataVencimento,
            Prioridade novaPrioridade) {

        boolean tarefaEncontrada = false;
        for (TarefaModel t : listaTarefas) {
            if (t.equals(tarefa)) {
            	tarefa.setTitulo(novoTitulo);
                tarefa.setDescricao(novaDescricao);
                tarefa.setDataVencimento(novaDataVencimento);
                tarefa.setPrioridade(novaPrioridade);
                tarefaEncontrada = true;
                break;
            }
        }
        if (!tarefaEncontrada) {
            throw new TarefaNaoEncontradaException("A tarefa fornecida não foi encontrada."); 
    }
        
    }

    public void excluirTarefa(TarefaModel tarefa) {
        boolean tarefaEncontrada = false;
        for (TarefaModel t : listaTarefas) {
            if (t.equals(tarefa)) {
                listaTarefas.remove(t);
                tarefaEncontrada = true;
                break;
            }
        }
        if (!tarefaEncontrada) {
            throw new TarefaNaoEncontradaException("A tarefa fornecida não foi encontrada.");
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

    private boolean validarDataVencimento(String dataVencimento) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); 

        try {
            Date data = sdf.parse(dataVencimento);
            Date dataAtual = new Date(); 
            
            if (data.after(dataAtual)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false; 
        }
    }

}
