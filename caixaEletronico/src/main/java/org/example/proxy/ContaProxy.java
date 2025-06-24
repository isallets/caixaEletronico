package org.example.proxy;

import org.example.contas.Conta;
import org.example.Usuario;

public class ContaProxy implements Conta {
    private Conta contaReal;
    private Usuario usuario;

    public ContaProxy(Conta contaReal, Usuario usuario) {
        this.contaReal = contaReal;
        this.usuario = usuario;
    }

    @Override
    public void exibirTipoConta() {
        if (usuario != null && usuario.isAutenticado()) {
            System.out.println("[Proxy] Acesso permitido.");
            contaReal.exibirTipoConta();
        } else {
            System.out.println("[Proxy] Acesso negado. Usuario nao autenticado.");
        }
    }
}
