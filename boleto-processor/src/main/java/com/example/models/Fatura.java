package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Fatura {

    private String data;
    private double valor;
    private String nomeCliente;
    private List<Boleto> boletos;
    private boolean pago;

    public Fatura(String data, double valor, String nomeCliente) {
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.boletos = new ArrayList<>();
        this.pago = false;
    }

    public void addBoleto(Boleto boleto) {
        this.boletos.add(boleto);
    }

    public void pagar() {
        double somaBoletos = 0;
        for (Boleto boleto : boletos) {
            somaBoletos += boleto.getValorPago();
        }
        if (somaBoletos >= this.valor) {
            this.pago = true;
        }
    }

    public boolean isPago() {
        return this.pago;
    }
}
