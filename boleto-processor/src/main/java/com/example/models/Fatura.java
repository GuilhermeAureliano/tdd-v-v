package com.example.models;

import java.util.ArrayList;
import java.util.List;

public class Fatura {

    private String data;
    private double valor;
    private String nomeCliente;
    private List<Boleto> boletos;
    private boolean pago;
    private List<Pagamento> pagamentos;

    public Fatura(String data, double valor, String nomeCliente) {
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.boletos = new ArrayList<>();
        this.pago = false;
        this.pagamentos = new ArrayList<>();
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
            for (Boleto boleto : boletos) {
                this.pagamentos.add(
                        new Pagamento(boleto.getValorPago(), boleto.getData(), "BOLETO"));
            }
        }
    }

    public boolean isPago() {
        return this.pago;
    }

    public List<Pagamento> getPagamentos() {
        return this.pagamentos;
    }
}
