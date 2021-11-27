package mainPack;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findByFullName(String fullName);
}
