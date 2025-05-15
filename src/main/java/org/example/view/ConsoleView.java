package org.example.view;

import org.example.controller.ContaController;
import org.example.model.Conta;
import org.example.model.TipoConta;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {

    private final ContaController controller;
    Scanner input = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public ConsoleView(ContaController controller) {
        this.controller = controller;
    }

    public void iniciar(){
        boolean executando = true;

        while (executando){
            System.out.println("\n===== SISTEMA DE GERENCIAMENTO DE CONTAS =====");
            System.out.println("1. Adicionar Conta");
            System.out.println("2. Listar todas as Contas");
            System.out.println("3. Total de contas a pagar");
            System.out.println("4. Total de contas a receber");
            System.out.println("5. Salto Geral");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String entrada = input.nextLine();

            int opcao;
            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número.");
                iniciar();
                return;
            }

            switch (opcao){
                case 1:
                    while (true) {
                        System.out.println("\n===== ADICIONAR NOVA CONTA =====");
                        System.out.println("Tipo de Conta:");
                        System.out.println("1. Conta a Pagar");
                        System.out.println("2. Conta a Receber");
                        System.out.print("Escolha o tipo (1/2): ");
                        String escolha = input.nextLine();
                        int tipo;
                        try {
                            tipo = Integer.parseInt(escolha);
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida. Digite um número válido.");
                            continue;
                        }

                        if (tipo == 1) {
                            cadastrarConta(TipoConta.PAGAR);
                            break;
                        } else if (tipo == 2) {
                            cadastrarConta(TipoConta.RECEBER);
                            break;
                        } else {
                            System.out.println("Tipo de conta inválido. Tente novamente.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("Listando todas as contas");
                    List<Conta> contas = controller.listarTodasContas();
                    for (Conta conta : contas){
                        System.out.println(conta);
                }
                    break;
                case 3:
                    System.out.println("\n===== TOTAL DE CONTAS A PAGAR =====");
                    Double totalContasPagar = controller.calcularTotalContasPagar();
                    System.out.printf("Total de Contas a Pagar: R$ %.2f%n", totalContasPagar);
                    break;
                case 4:
                    System.out.println("\n===== TOTAL DE CONTAS A RECEBER =====");
                    Double totalContasReceber = controller.calcularTotalContasReceber();
                    System.out.printf("Total de Contas a Receber: R$ %.2f%n", totalContasReceber);
                    break;
                case 5:
                    System.out.println("\n===== RESUMO FINANCEIRO =====");
                    Double saldoGeral = controller.calcularSaldo();
                    System.out.printf("Saldo Geral: R$ %.2f%n", saldoGeral);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");

            }
        }


    }
    private void cadastrarConta(TipoConta tipoConta) {
        System.out.print("Descrição: ");
        String descricao = input.nextLine().toLowerCase();

        double valor = 0;
        while (true) {
            System.out.print("Valor (R$): ");
            String entradaValor = input.nextLine();
            try {
                valor = Double.parseDouble(entradaValor);
                if (valor <= 0) {
                    System.out.println("O valor deve ser maior que zero.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Tente novamente!");
            }
        }
        System.out.print("Data de vencimento (dd/MM/yyyy): ");
        String dataTexto = input.nextLine();
        if (dataTexto.trim().isEmpty()) {
            System.out.println("Data não pode estar vazia.");
            return;
        }
        try {
            LocalDate dataVencimento = LocalDate.parse(dataTexto, formatter);
            controller.adicionarConta(valor, descricao, dataVencimento, tipoConta);
            System.out.println("Conta cadastrada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar conta: " + e.getMessage());
        }
    }

}
