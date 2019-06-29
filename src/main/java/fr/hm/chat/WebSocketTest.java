package fr.hm.chat;

import fr.hm.chat.config.topic.Topic;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

public class WebSocketTest {

    public static void main(String[] args) {
        WebSocketClient client = new ReactorNettyWebSocketClient();
        client.execute(
                URI.create("ws://localhost:8082"+ Topic.EVENT_PROD.getTopic()),
                session -> session.send(
                        Mono.just(session.textMessage("event-spring-reactive-client-websocket")))
                        .thenMany(session.receive()
                                .map(WebSocketMessage::getPayloadAsText)
                                .log())
                        .then())
                .block(Duration.ofSeconds(10L));
    }
}
