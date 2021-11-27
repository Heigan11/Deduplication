package mainPack;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static mainPack.Sources.topicName;
import static mainPack.Sources.msg;

@Component
public class Consumer {

    String requestUrl="http://localhost:5006/send";

    @KafkaListener(topics = topicName)
    public void listenTopic(ConsumerRecord<String, String> record) {
        if (record.key().equals("updateClient")) {
            msg = record.value();
            System.out.println(msg);
            sendMsg(msg);
        }
    }

    public void sendMsg(String msg) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<String>(msg);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity( requestUrl, request , String.class );
    }
}
