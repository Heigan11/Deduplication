package examplePack;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static examplePack.Sources.counter;
import static examplePack.Sources.topicName;

@RestController
@CrossOrigin
public class RestControllerExample {

    @Autowired
    ClientRepository clientRepository;

    KafkaConfig kafkaConfig = new KafkaConfig();
    KafkaTemplate<String, String> template = kafkaConfig.kafkaTemplate();

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/send")
    public String catchRandomMessage(@RequestBody String msg) throws JsonProcessingException {
        Client client = objectMapper.readValue(msg, Client.class);

        List<Client> clients = clientRepository.findByFullName(client.getFullName());
        if (!clients.isEmpty()) {
            for (Client foo : clients) {
                if (foo.getBirthDate().equals(client.getBirthDate()) && foo.getPassport().equals(client.getPassport()) && !foo.isToDelete()){
                    foo.setToDelete(true);
                    client.setOldClientId(foo.getClientId());
                    client.setClientId(UUID.randomUUID().toString());
                    clientRepository.save(client);
                    String result = objectMapper.writeValueAsString(client);
                    template.send(topicName, "updateClient", result);
                }
            }
            return msg;
        }
        client.setClientId(UUID.randomUUID().toString());
        clientRepository.save(client);
        return msg;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Client> getAllUsers() {
        return clientRepository.findAll();
    }
}

//cd /opt/kafka_2.13-3.0.0
//sudo bin/zookeeper-server-start.sh config/zookeeper.properties
//sudo bin/kafka-server-start.sh config/server.properties
//sudo bin/kafka-topics.sh --create --topic mytest --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
//sudo bin/kafka-console-consumer.sh --topic mytest --from-beginning --bootstrap-server localhost:9092
//cd ~/'Рабочий стол'/rest/frontend
//npm start