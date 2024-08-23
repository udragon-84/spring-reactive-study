package com.weverse.votestorage;

import com.weverse.votestorage.service.KafkaSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class KafkaConsumerRunner implements CommandLineRunner {

    @Autowired
    private List<KafkaSubscribe> kafkaSubscribeList;

    @Override
    public void run(String... args) throws Exception {
        kafkaSubscribeList.forEach(kafkaSubscribe -> {
            kafkaSubscribe.consumeAndSave()
                    .subscribe();
        });
    }
}
