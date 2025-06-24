package org.example.service;

import org.example.Usuario;
import org.example.contas.Conta; // Adicionar de volta se o IDE remover
import org.example.contasfactory.ContaFactory; // Adicionar de volta se o IDE remover

import java.util.Optional;

public interface UsuarioService {
    Usuario cadastrarUsuario(String nome, String senha, int tipoConta);
    Optional<Usuario> autenticarUsuario(String nome, String senha);
    // NOVO MÉTODO: Retorna a Conta do usuário (opcionalmente)
    Optional<Conta> getContaDoUsuario(String nomeUsuario); // <-- Adicione esta linha
}