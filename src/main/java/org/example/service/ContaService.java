package org.example.service;

import org.example.model.Conta;
import org.example.model.TipoConta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContaService {

     List<Conta> listaContas = new ArrayList<>();

    public void adicionarConta(double valor, String descricao, LocalDate dataVencimento, TipoConta tipoConta) {
        Conta conta = new Conta(tipoConta, dataVencimento, valor, descricao);
        listaContas.add(conta);
    }

    public List<Conta> listarTodasContas() {
      return listaContas;
    }


    public Double calcularTotalContasPagar() {
        return listaContas.stream()
                .filter(conta -> conta.getTipoConta() == TipoConta.PAGAR && !conta.isPago())
                .mapToDouble(Conta::getValor)
                .sum();
    }

    public Double calcularTotalContasReceber(){
        return listaContas.stream()
                .filter(conta -> conta.getTipoConta() == TipoConta.RECEBER && !conta.isPago())
                .mapToDouble(Conta::getValor)
                .sum();
    }

    public double calcularSaldoGeral() {
        return calcularTotalContasReceber() - calcularTotalContasPagar();
    }
}
