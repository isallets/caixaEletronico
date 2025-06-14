package org.example;

import org.example.contas.Conta;
import org.example.contasfactory.ContaFactory;

public class Usuario {
    private String nome;
    private String senha;
    private Conta conta;

    public Usuario(String nome, String senha, ContaFactory factory) {
        this.nome = nome;
        this.senha = senha;
        this.conta = factory.criarConta();
    }

    public boolean autenticar(String nome, String senha) {
        return this.nome.equals(nome) && this.senha.equals(senha);
    }

    public void exibirConta() {
        conta.exibirTipoConta();
    }
}
