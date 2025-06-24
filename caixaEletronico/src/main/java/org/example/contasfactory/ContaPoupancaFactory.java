package org.example.contasfactory;

import org.example.contas.Conta;
import org.example.contas.ContaPoupanca;

public class ContaPoupancaFactory extends ContaFactory {
    @Override
    public Conta criarConta() {
        return new ContaPoupanca();
    }
}
