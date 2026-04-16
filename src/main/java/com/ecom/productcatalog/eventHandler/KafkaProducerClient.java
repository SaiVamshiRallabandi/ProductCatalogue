package com.ecom.productcatalog.eventHandler;

import org.apache.kafka.clients.KafkaClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerClient {

    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducerClient( KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    public void sendMessage(String topic,String key ,String message){
        kafkaTemplate.send(topic,key,message);
    }
}
