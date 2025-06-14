package org.example.contas;

public class ContaCorrente implements Conta {
    @Override
    public void exibirTipoConta() {
        System.out.println("Conta Corrente criada.");
    }
}
