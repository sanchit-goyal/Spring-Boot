package com.sam.kafkaconsumer.listner;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListner {

    @KafkaListener(groupId = "GROUP_1", topics = "TOPIC_1")
    public void kafkaStringConsumer(String message) {
        System.out.println(message);
    }

}
