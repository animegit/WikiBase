package com.spring;

import com.spring.Entity.WikimediaData;
import com.spring.Repository.WikiMedaiRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDatabase {

    private static final Logger log= LoggerFactory.getLogger(KafkaConsumerDatabase.class);

   @Autowired
   private WikiMedaiRepo wikiMedaiRepo;
    @KafkaListener(topics = "wikimedia",groupId = "myGroup")
    public void consume(String eventMsg){
        log.info(String.format("Message received is-> %s",eventMsg));
        WikimediaData wikimediaData=new WikimediaData();
        wikimediaData.setData(eventMsg);
        wikiMedaiRepo.save(wikimediaData);

    }
}
