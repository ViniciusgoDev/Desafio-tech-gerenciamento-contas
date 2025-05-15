package org.example.controller;

import org.example.model.Conta;
import org.example.model.TipoConta;
import org.example.service.ContaService;
import java.time.LocalDate;
import java.util.List;

public class ContaController {

    ContaService contaService = new ContaService();

    public ContaController() {
        this.contaService = contaService;
    }

    public void adicionarConta(double valor, String descricao,
                                LocalDate dataVencimento,
                                TipoConta tipoConta){

    contaService.adicionarConta(valor, descricao, dataVencimento, tipoConta);
    }

    public List<Conta> listarTodasContas(){
        return contaService.listarTodasContas();
    }

    public Double calcularTotalContasPagar(){
        return contaService.calcularTotalContasPagar();
    }
    public Double calcularTotalContasReceber(){
        return contaService.calcularTotalContasReceber();
    }

    public Double calcularSaldo(){
        return contaService.calcularSaldoGeral();
    }



}
