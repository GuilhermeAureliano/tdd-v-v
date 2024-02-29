package com.example.models;

public class Pagamento {
    private double valorPago;
    private String data;
    private String tipoPagamento;

    public Pagamento(double valorPago, String data, String tipoPagamento) {
        this.valorPago = valorPago;
        this.data = data;
        this.tipoPagamento = tipoPagamento;
    }

    public String getData() {
        return data;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public double getValorPago() {
        return valorPago;
    }
}
