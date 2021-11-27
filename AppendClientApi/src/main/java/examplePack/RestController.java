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

@org.springframework.web.bind.annotation.RestController
@CrossOrigin
public class RestController {

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