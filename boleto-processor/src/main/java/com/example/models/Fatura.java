package com.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fatura {

    private final String data;
    private final double valor;
    private final String nomeCliente;
    private final List<Boleto> boletos;
    private boolean pago;
    private final List<Pagamento> pagamentos;

    public Fatura(String data, double valor, String nomeCliente) {
        this.data = data;
        this.valor = valor;
        this.nomeCliente = nomeCliente;
        this.boletos = new ArrayList<>();
        this.pago = false;
        this.pagamentos = new ArrayList<>();
    }

    public void addBoleto(Boleto boleto) {
        this.boletos.add(Objects.requireNonNull(boleto, "Boleto não pode ser nulo"));
    }

    public void pagar() {
        double somaBoletos = calcularTotalBoletos();
        if (somaBoletos >= this.valor) {
            this.pago = true;
            efetuarPagamento();
        }
    }

    private double calcularTotalBoletos() {
        double somaBoletos = 0;
        for (Boleto boleto : boletos) {
            somaBoletos += boleto.getValorPago();
        }
        return somaBoletos;
    }

    private void efetuarPagamento() {
        for (Boleto boleto : boletos) {
            this.pagamentos.add(
                    new Pagamento(boleto.getValorPago(), boleto.getData(), "BOLETO"));
        }
    }

    public boolean isPago() {
        return this.pago;
    }

    public List<Pagamento> getPagamentos() {
        return this.pagamentos;
    }
}
