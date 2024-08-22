package com.weverse.votestorage;

import com.weverse.votestorage.service.KafkaSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaConsumerRunner implements CommandLineRunner {

    @Autowired
    private List<KafkaSubscribe> kafkaSubscribeList;

    @Override
    public void run(String... args) throws Exception {
        kafkaSubscribeList.forEach(KafkaSubscribe::consumeAndSave);
    }
}
