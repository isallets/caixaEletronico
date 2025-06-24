package org.example;

import org.example.contas.Conta;
import org.example.contasfactory.ContaFactory;
import org.example.proxy.ContaProxy;

public class Usuario {
    private String nome;
    private String senha;
    private Conta conta;
    private boolean autenticado;

    public Usuario(String nome, String senha, ContaFactory factory) {
        this.nome = nome;
        this.senha = senha;
        this.conta = factory.criarConta();
        this.autenticado = false;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public boolean autenticar(String nome, String senha) {
        if (this.nome.equals(nome) && this.senha.equals(senha)) {
            this.autenticado = true;
            return true;
        }
        return false;
    }

    public boolean isAutenticado() {
        return autenticado;
    }

}
