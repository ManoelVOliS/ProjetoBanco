package com.banco;

import java.util.Scanner;

public class BancoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Banco banco = new Banco();
        int opcao;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Consultar Cliente por CPF");
            System.out.println("4. Remover Cliente");
            System.out.println("5. Criar Conta");
            System.out.println("6. Listar Contas de Cliente");
            System.out.println("7. Remover Conta");
            System.out.println("8. Realizar Depósito");
            System.out.println("9. Realizar Saque");
            System.out.println("10. Transferir");
            System.out.println("11. Consultar Saldo");
            System.out.println("12. Consultar Balanço");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do Cliente: ");
                    String nome = scanner.next();
                    System.out.print("CPF do Cliente: ");
                    String cpf = scanner.next();
                    banco.cadastrarCliente(nome, cpf); 
                    break;
                case 2:
                    banco.listarClientes();
                    break;
                case 3:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    Cliente cliente = banco.consultarClientePorCpf(cpf);
                    if (cliente != null) {
                        System.out.println("Cliente encontrado: " + cliente.getNome());
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    banco.removerCliente(cpf);
                    break;
                case 5:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    banco.criarConta(cpf); 
                    break;
                case 6:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    banco.listarContasCliente(cpf);
                    break;
                case 7:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    System.out.print("Número da Conta: ");
                    int numeroConta = scanner.nextInt();
                    banco.removerConta(cpf, numeroConta);
                    break;
                case 8:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    System.out.print("Número da Conta: ");
                    numeroConta = scanner.nextInt();
                    System.out.print("Valor do Depósito: ");
                    double valorDeposito = scanner.nextDouble();
                    banco.realizarDeposito(cpf, numeroConta, valorDeposito);
                    break;
                case 9:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    System.out.print("Número da Conta: ");
                    numeroConta = scanner.nextInt();
                    System.out.print("Valor do Saque: ");
                    double valorSaque = scanner.nextDouble();
                    banco.realizarSaque(cpf, numeroConta, valorSaque);
                    break;
                case 10:
                    System.out.print("CPF de Origem: ");
                    String cpfOrigem = scanner.next();
                    System.out.print("Número da Conta de Origem: ");
                    int numeroContaOrigem = scanner.nextInt();
                    System.out.print("CPF de Destino: ");
                    String cpfDestino = scanner.next();
                    System.out.print("Número da Conta de Destino: ");
                    int numeroContaDestino = scanner.nextInt();
                    System.out.print("Valor da Transferência: ");
                    double valorTransferencia = scanner.nextDouble();
                    banco.realizarTransferencia(cpfOrigem, numeroContaOrigem, cpfDestino, numeroContaDestino, valorTransferencia);
                    break;
                case 11:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    System.out.print("Número da Conta: ");
                    numeroConta = scanner.nextInt();
                    banco.consultarSaldo(cpf, numeroConta);
                    break;
                case 12:
                    System.out.print("CPF do Cliente: ");
                    cpf = scanner.next();
                    System.out.print("Número da Conta: ");
                    numeroConta = scanner.nextInt();
                    banco.consultarBalanço(cpf);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
