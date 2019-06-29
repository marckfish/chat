# chat

**Classe WebSocketConfig**

     @Configuration
     @EnableWebSocketMessageBroker
     public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
     //1   
     @Override
     public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint("/socket")
         //2 
         .setAllowedOrigins("*")
         .withSockJS();
     }
     //3   
     @Override
     public void configureMessageBroker(MessageBrokerRegistry config){
         config.enableSimpleBroker("/topic/");
         config.setApplicationDestinationPrefixes("/app");
     }}
_1_

Méthode permettant de définir l'endpoint que nos client utiliseront pour se connecter au serveur

    http://localhost:8082/socket
 
_2_    

Permet de recevoir les requêtes de n'importe quelle origine.

_3_

Méthode permettant de configurer nos topics. Nous avons déclaré un seul topic portant le nom **topic**, les 
client peuvent s'abonner à ce topic pour recevoir les messages du serveur.

Lorsque les clients vont envoyer leurs messages ils doivent préfixer leurs endpoints par /app

    http://localhost:8082/app/send/message
    
**Classe MessagingController**

    `
    //1
    @MessageMapping("/send/message")
    //2 
    @SendTo("/topic/message")
    public String broadcastNews(@Payload String message) {
        System.out.println("message " +message);
        return message;
    }`
    
A chaque fois qu'un client invoque l'url [http://localhost:8082/app/send/message] un message est envoyé 
à tous les clients abonnés au topic **`topic`**