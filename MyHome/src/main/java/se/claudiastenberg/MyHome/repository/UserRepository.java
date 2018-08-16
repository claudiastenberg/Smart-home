package se.claudiastenberg.MyHome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.claudiastenberg.MyHome.model.User;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{
    Optional<User> findByAccountName(Long accountName);
}
