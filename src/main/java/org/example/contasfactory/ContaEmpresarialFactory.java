package org.example.contasfactory;

import org.example.contas.Conta;
import org.example.contas.ContaEmpresarial;

public class ContaEmpresarialFactory extends ContaFactory {
    @Override
    public Conta criarConta() {
        return new ContaEmpresarial();
    }
}
