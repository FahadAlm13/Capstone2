package spring.boot.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.Credit;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit,Integer>{
    Credit findCreditById(Integer id);

    Credit findCreditByDriverId(Integer driverId);

    @Query("SELECT c FROM Credit c WHERE c.driverId = ?1")
    List<Credit> findByDriverId(Integer driverId);

    @Modifying
    @Query("UPDATE Credit c SET c.points = c.points + ?2 WHERE c.driverId = ?1")
    void addPoints(Integer driverId, int points);

    @Modifying
    @Query("UPDATE Credit c SET c.points = 0 WHERE c.driverId = ?1")
    void resetPoints(Integer driverId);
}
