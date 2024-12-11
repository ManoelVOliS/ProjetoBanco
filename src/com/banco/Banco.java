package com.banco;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private List<Cliente> clientes;
    private static int contadorDeContas = 0;

    public Banco() {
        this.clientes = new ArrayList<>();
    }

    public void cadastrarCliente(String nome, String cpf) {
        if (consultarClientePorCpf(cpf) == null) {
            Cliente cliente = new Cliente(nome, cpf);
            clientes.add(cliente);
            criarConta(cpf); 
            System.out.println("Cliente cadastrado com sucesso: " + nome);
        } else {
            System.out.println("Cliente com CPF " + cpf + " já existe.");
        }
    }

    public Cliente consultarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Não há clientes cadastrados.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println("Nome: " + cliente.getNome() + ", CPF: " + cliente.getCpf());
            }
        }
    }

    
    public void criarConta(String cpf) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            Conta novaConta = new Conta(contadorDeContas++);  
            cliente.adicionarConta(novaConta);
            System.out.println("Conta criada com sucesso! Número da conta: " + novaConta.getNumero());
        } else {
            System.out.println("Cliente não encontrado para criação de conta.");
        }
    }

    public void listarContasCliente(String cpf) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            if (cliente.getContas().isEmpty()) {
                System.out.println("O cliente não tem contas cadastradas.");
            } else {
                for (Conta conta : cliente.getContas()) {
                    System.out.println("Conta número: " + conta.getNumero() + ", Saldo: " + conta.getSaldo());
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void removerCliente(String cpf) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void removerConta(String cpf, int numeroConta) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            Conta conta = cliente.consultarConta(numeroConta);
            if (conta != null) {
                cliente.removerConta(conta);
                System.out.println("Conta " + numeroConta + " removida com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarDeposito(String cpf, int numeroConta, double valor) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            Conta conta = cliente.consultarConta(numeroConta);
            if (conta != null) {
                conta.depositar(valor);
                System.out.println("Depósito de " + valor + " realizado com sucesso.");
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarSaque(String cpf, int numeroConta, double valor) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            Conta conta = cliente.consultarConta(numeroConta);
            if (conta != null) {
                if (conta.sacar(valor)) {
                    System.out.println("Saque de " + valor + " realizado com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void realizarTransferencia(String cpfOrigem, int numeroContaOrigem, String cpfDestino, int numeroContaDestino, double valor) {
        Cliente clienteOrigem = consultarClientePorCpf(cpfOrigem);
        Cliente clienteDestino = consultarClientePorCpf(cpfDestino);
        if (clienteOrigem != null && clienteDestino != null) {
            Conta contaOrigem = clienteOrigem.consultarConta(numeroContaOrigem);
            Conta contaDestino = clienteDestino.consultarConta(numeroContaDestino);
            if (contaOrigem != null && contaDestino != null) {
                if (contaOrigem.transferir(valor, contaDestino)) {
                    System.out.println("Transferência de " + valor + " realizada com sucesso.");
                } else {
                    System.out.println("Saldo insuficiente.");
                }
            } else {
                System.out.println("Conta de origem ou destino não encontrada.");
            }
        } else {
            System.out.println("Cliente de origem ou destino não encontrado.");
        }
    }

    public void consultarSaldo(String cpf, int numeroConta) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            Conta conta = cliente.consultarConta(numeroConta);
            if (conta != null) {
                System.out.println("Saldo da conta " + numeroConta + ": " + conta.getSaldo());
            } else {
                System.out.println("Conta não encontrada.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void consultarBalanço(String cpf) {
        Cliente cliente = consultarClientePorCpf(cpf);
        if (cliente != null) {
            double saldoTotal = 0;
            for (Conta conta : cliente.getContas()) {
                saldoTotal += conta.getSaldo();
            }
            System.out.println("Balanço total do cliente " + cpf + ": " + saldoTotal);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}

