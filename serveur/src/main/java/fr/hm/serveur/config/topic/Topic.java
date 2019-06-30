package fr.hm.serveur.config.topic;

import lombok.Getter;

public enum Topic {

    EVENT_PROD("/event/prod");

    @Getter
    public String topic;

    Topic(String topic){
        this.topic = topic;
    }

}
