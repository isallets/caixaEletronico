package org.example.websocket;

import org.example.dto.ContaResponse; // Use o DTO já criado
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ContaWebSocketController {

    // Este método será chamado quando um cliente enviar uma mensagem para "/app/conta.notificacao"
    // (onde "/app" é o applicationDestinationPrefix configurado em WebSocketConfig)
    @MessageMapping("/conta.notificacao")
    @SendTo("/topic/public") // Envia a resposta para todos os assinantes do tópico "/topic/public"
    public ContaResponse sendContaNotification(String message) {
        // Em uma aplicação real, você processaria a mensagem recebida e talvez consultaria um serviço.
        // Por enquanto, vamos retornar uma notificação genérica.
        System.out.println("Mensagem WebSocket recebida: " + message);
        return new ContaResponse("Notificação Geral", "Mensagem do servidor: " + message, true);
    }

    // Para o requisito de Observer/Observable no back-end (push de atualizações):
    // Você pode injetar 'SimpMessagingTemplate' em seus serviços para enviar mensagens
    // para o front-end quando houver uma atualização de dados (Ex: nova conta criada).
    /*
    private final SimpMessagingTemplate messagingTemplate;

    public ContaWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    // Exemplo de como um serviço poderia notificar o front-end
    public void notificarNovaConta(String nomeUsuario, String tipoConta) {
        ContaResponse response = new ContaResponse(tipoConta, "Nova conta criada para " + nomeUsuario, true);
        // Envia para todos que estão inscritos em "/topic/contas.novas"
        messagingTemplate.convertAndSend("/topic/contas.novas", response);
    }
    */
}