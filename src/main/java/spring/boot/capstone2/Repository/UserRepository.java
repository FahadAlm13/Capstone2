package spring.boot.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.User;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {

    User findUsersById(Integer id);

    List<User> findByIsPrime(boolean isPrime);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findUserByEmail(String email);
}

