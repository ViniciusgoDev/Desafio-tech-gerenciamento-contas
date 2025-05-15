package org.example.model;

import java.time.LocalDate;
import java.util.Date;

public class Conta {

    private String descricao;
    private Double valor;
    private LocalDate dataVencimento;
    private TipoConta tipoConta;
    private boolean isPago;

    public Conta(TipoConta tipoConta, LocalDate dataVencimento, Double valor, String descricao) {
        this.tipoConta = tipoConta;
        this.dataVencimento = dataVencimento;
        this.valor = valor;
        this.descricao = descricao;
    }

    public boolean isPago() {
        return isPago;
    }

    public void setPago(boolean pago) {
        isPago = pago;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("| Tipo: %s | Descrição: %s | R$ %.2f | Vencimento: %s | Status: %s ",
                tipoConta,
                descricao,
                valor,
                dataVencimento,
                isPago ? "PAGO" : "PENDENTE");
    }
}
