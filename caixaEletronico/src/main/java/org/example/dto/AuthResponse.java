package org.example.dto;

public class AuthResponse {
    private String nomeUsuario;
    private String mensagem;
    private boolean sucesso;

    public AuthResponse(String nomeUsuario, String mensagem, boolean sucesso) {
        this.nomeUsuario = nomeUsuario;
        this.mensagem = mensagem;
        this.sucesso = sucesso;
    }

    // Getters
    public String getNomeUsuario() { return nomeUsuario; }
    public String getMensagem() { return mensagem; }
    public boolean isSucesso() { return sucesso; }
}