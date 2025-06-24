package org.example.service;

import org.example.Usuario;
import org.example.contas.Conta;
import org.example.contas.ContaCorrente;
import org.example.contas.ContaEmpresarial;
import org.example.contas.ContaPoupanca;
import org.example.contasfactory.ContaCorrenteFactory;
import org.example.contasfactory.ContaEmpresarialFactory;
import org.example.contasfactory.ContaFactory;
import org.example.contasfactory.ContaPoupancaFactory;
import org.example.dto.ContaResponse; // Importe o DTO
import org.springframework.messaging.simp.SimpMessagingTemplate; // Importe SimpMessagingTemplate
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final List<Usuario> usuarios = new ArrayList<>();
    private final SimpMessagingTemplate messagingTemplate; // Injete SimpMessagingTemplate

    // Construtor atualizado para injeção
    public UsuarioServiceImpl(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public Usuario cadastrarUsuario(String nome, String senha, int tipoConta) {
        ContaFactory factory;
        String tipoContaStr; // Para usar na notificação
        switch (tipoConta) {
            case 1:
                factory = new ContaCorrenteFactory();
                tipoContaStr = "Corrente";
                break;
            case 2:
                factory = new ContaPoupancaFactory();
                tipoContaStr = "Poupança";
                break;
            case 3:
                factory = new ContaEmpresarialFactory();
                tipoContaStr = "Empresarial";
                break;
            default:
                throw new IllegalArgumentException("Tipo de conta inválido.");
        }

        Usuario novoUsuario = new Usuario(nome, senha, factory);
        usuarios.add(novoUsuario);

        // **** NOVO: Notificar via WebSocket sobre a nova conta ****
        String message = "Nova conta " + tipoContaStr + " criada para o usuário: " + nome;
        ContaResponse notification = new ContaResponse(tipoContaStr, message, true);
        // Envia para todos os clientes que estão inscritos em "/topic/contas.novas"
        messagingTemplate.convertAndSend("/topic/contas.novas", notification);
        System.out.println("Notificação WebSocket enviada: " + message); // Log para verificar

        return novoUsuario;
    }

    @Override
    public Optional<Usuario> autenticarUsuario(String nome, String senha) {
        for (Usuario usuario : usuarios) {
            if (usuario.autenticar(nome, senha)) {
                // Exemplo de notificação de login (opcional)
                messagingTemplate.convertAndSend("/topic/logins", "Usuário " + nome + " logou!");
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Conta> getContaDoUsuario(String nomeUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nomeUsuario) && usuario.isAutenticado()) {
                return Optional.ofNullable(usuario.getConta());
            }
        }
        return Optional.empty();
    }
}