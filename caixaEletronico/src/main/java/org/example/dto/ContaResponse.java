package org.example.dto;

public class ContaResponse {
    private String tipoConta;
    private String mensagem;
    private boolean sucesso;

    public ContaResponse(String tipoConta, String mensagem, boolean sucesso) {
        this.tipoConta = tipoConta;
        this.mensagem = mensagem;
        this.sucesso = sucesso;
    }

    // Getters
    public String getTipoConta() { return tipoConta; }
    public String getMensagem() { return mensagem; }
    public boolean isSucesso() { return sucesso; }
}