package spring.boot.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.Driver;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Driver findDriverById(Integer id);

    @Query("SELECT d FROM Driver d WHERE d.experience >= ?1")
    List<Driver> findByExperienceGreaterThanEqual(int years);

    @Query("SELECT d FROM Driver d ORDER BY d.experience DESC")
    List<Driver> findTopDrivers();

    @Modifying
    @Query("UPDATE Driver d SET d.experience = d.experience + ?2 WHERE d.id = ?1")
    void updateDriverExperience(Integer driverId, int experience);
}
