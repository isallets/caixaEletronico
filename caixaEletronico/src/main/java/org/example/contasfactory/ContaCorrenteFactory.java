package org.example.contasfactory;

import org.example.contas.Conta;
import org.example.contas.ContaCorrente;

public class ContaCorrenteFactory extends ContaFactory {
    @Override
    public Conta criarConta() {
        return new ContaCorrente();
    }
}
