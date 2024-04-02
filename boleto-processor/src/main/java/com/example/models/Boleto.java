package com.example.models;

public class Boleto {

    private String codigo;
    private String data;
    private double valorPago;

    public Boleto(String codigo, String data, double valorPago) {
        if (valorPago <= 0) {
            throw new IllegalArgumentException("Valor do boleto deve ser maior que zero");
        }
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getData() {
        return data;
    }

    public double getValorPago() {
        return valorPago;
    }
}
