package mainPack.UpdateClientApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ClientRepository clientRepository;

    @PostMapping(path="/send") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestBody String msg) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Client n = objectMapper.readValue(msg, Client.class);

        List<Client> clients = clientRepository.findByClientId(n.getOldClientId());
        if (!clients.isEmpty()) {
            clients.get(0).setToDelete("true");
        }
        clientRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Client> getAllUsers() {
        return clientRepository.findAll();
    }
}