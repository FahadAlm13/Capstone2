package spring.boot.capstone2.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Review findReviewById(Integer id);

    @Query("SELECT r FROM Review r WHERE r.driverId = ?1")
    List<Review> findReviewsByDriverId(Integer driverId);

    @Query("SELECT r FROM Review r WHERE r.equipmentId = ?1")
    List<Review> findReviewsByEquipmentId(Integer equipmentId);

    @Query("SELECT r FROM Review r WHERE r.userId = ?1")
    List<Review> findReviewsByUserId(Integer userId);
}