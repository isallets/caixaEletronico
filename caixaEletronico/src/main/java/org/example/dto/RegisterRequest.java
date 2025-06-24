package org.example.dto;

public class RegisterRequest {
    private String nome;
    private String senha;
    private int tipoConta;

    // Getters e Setters (gerados automaticamente pelo IDE ou digitados)
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public int getTipoConta() { return tipoConta; }
    public void setTipoConta(int tipoConta) { this.tipoConta = tipoConta; }
}