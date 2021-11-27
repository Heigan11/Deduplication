package examplePack;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.UUID;

import static examplePack.Sources.topicName;

@SpringBootApplication
@EnableAdminServer
public class AppendClientApi {

    public static void main(String[] args) {
        SpringApplication.run(AppendClientApi.class);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                KafkaConfig kafkaConfig = new KafkaConfig();
                KafkaTemplate<String, String> template = kafkaConfig.kafkaTemplate();

                while (true) {
                    template.send(topicName, UUID.randomUUID().toString(), UUID.randomUUID().toString());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
    }
}
