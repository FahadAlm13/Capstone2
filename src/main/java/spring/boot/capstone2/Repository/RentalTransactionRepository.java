package spring.boot.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.RentalTransaction;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RentalTransactionRepository extends JpaRepository<RentalTransaction,Integer> {

    RentalTransaction findRentalTransactionById(Integer id);

    @Query("SELECT r FROM RentalTransaction r WHERE r.customerId = ?1")
    List<RentalTransaction> findByCustomerId(Integer customerId);

    @Query("SELECT r FROM RentalTransaction r WHERE r.equipmentId = ?1")
    List<RentalTransaction> findByEquipmentId(Integer equipmentId);

    @Modifying
    @Query("UPDATE RentalTransaction r SET r.endDate = ?2 WHERE r.id = ?1")
    void extendRentalDuration(Integer rentalTransactionId, LocalDate newEndDate);

    @Modifying
    @Query("UPDATE RentalTransaction r SET r.driverId = ?2 WHERE r.id = ?1")
    void assignDriverToRental(Integer rentalTransactionId, Integer driverId);
}
