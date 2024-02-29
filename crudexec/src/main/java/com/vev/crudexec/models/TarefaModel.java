package com.vev.crudexec.models;

public class TarefaModel implements Comparable<TarefaModel> {
    private String titulo;
    private String descricao;
    private String dataVencimento;
    private Prioridade prioridade;

    public TarefaModel(String titulo, String descricao, String dataVencimento, Prioridade prioridade) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.prioridade = prioridade;
    }
    

    public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public String getDataVencimento() {
		return dataVencimento;
	}


	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}


	public Prioridade getPrioridade() {
		return prioridade;
	}


	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}


	@Override
    public int compareTo(TarefaModel outraTarefa) {
        if (!this.dataVencimento.equals(outraTarefa.dataVencimento)) {
            return this.dataVencimento.compareTo(outraTarefa.dataVencimento);
        } else {
            return this.prioridade.compareTo(outraTarefa.prioridade);
        }
    }
}