$(document).ready(function(){
    connect();
});

var webSocketProd = null;
var stompClient = null;

function sendMessage (){

    stompClient.send("/app/send/message" , {}, $('#test').val());
    $('#test').val('');
}

function connect() {
    if (webSocketProd === null){
        webSocketProd = new SockJS('http://localhost:8082/ws');
        stompClient = Stomp.over(webSocketProd);
    }

    stompClient.connect({}, this.onConnected.bind(this), this.onError.bind(this));

}

function onError(error) {
    console.log(error);
}

function onConnected() {

    this.stompClient.subscribe('/topic/message', this.onMessageReceived);

}

function onMessageReceived(message) {
    $("#chat").append("<div class='message'>"+message.body+"</div>")
    console.log('Message reçu', message.body);
}

