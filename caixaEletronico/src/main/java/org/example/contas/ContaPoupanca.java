package org.example.contas;

public class ContaPoupanca implements Conta {
    @Override
    public void exibirTipoConta() {
        System.out.println("Conta Poupanca criada.");
    }
}
