package com.example.models;

import java.util.List;

public class ProcessadorBoletos {

    public void processa(List<Boleto> boletos, Fatura fatura) {
        double valorTotal = 0;
        for (Boleto boleto : boletos) {
            Pagamento pagamento = new Pagamento(boleto.getValorPago(), boleto.getData(), "BOLETO");
            fatura.getPagamentos().add(pagamento);
            valorTotal += boleto.getValorPago();
        }

        if (valorTotal >= fatura.getValor()) {
            fatura.setPago(true);
        }
    }
}
