package se.claudiastenberg.MyHome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.claudiastenberg.MyHome.model.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long>{

    /*getUserById
    * POST
    * {
    *   "password":"123"
    * }
    *
    * Hitta ett ID som tillhör password 123 i databasen
    *
    * */
}
