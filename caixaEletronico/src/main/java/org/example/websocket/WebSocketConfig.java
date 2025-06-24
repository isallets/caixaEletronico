package org.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // Habilita o broker de mensagens STOMP sobre WebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // Define um prefixo para mensagens que são roteadas para o broker de mensagens.
        // Assinaturas do cliente para "/topic/..." serão roteadas para o broker para serem broadcast.
        config.enableSimpleBroker("/topic");

        // Define um prefixo para mensagens que são roteadas para @MessageMapping métodos (controladores WebSocket).
        // Clientes enviarão mensagens para "/app/..." que serão tratadas pelos controladores.
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Registra um endpoint STOMP para o WebSocket.
        // Clientes se conectarão a este endpoint para iniciar a comunicação WebSocket.
        // .withSockJS() é útil para navegadores antigos que não suportam WebSocket nativo,
        // fornecendo um fallback (não estritamente necessário se você focar em navegadores modernos).
        registry.addEndpoint("/ws")
                .setAllowedOrigins("https://localhost:3600") // Substitua pela URL do seu front-end (HTTPS)
                .withSockJS(); // Adicione SockJS para compatibilidade com navegadores mais antigos.
    }
}