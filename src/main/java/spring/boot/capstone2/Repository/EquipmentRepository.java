package spring.boot.capstone2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.capstone2.Model.Equipment;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {

    Equipment findEquipmentById(Integer id);

    @Query("SELECT e FROM Equipment e WHERE e.type = ?1")
    List<Equipment> findByType(String type);

    @Query("SELECT e FROM Equipment e WHERE e.status = ?1")
    List<Equipment> findByStatus(String status);

    @Query("SELECT e FROM Equipment e WHERE e.status = 'Available'")
    List<Equipment> findAvailableEquipment();

    @Modifying
    @Query("UPDATE Equipment e SET e.status = ?2 WHERE e.id = ?1")
    void updateEquipmentStatus(Integer equipmentId, String status);

    @Modifying
    @Query("UPDATE Equipment e SET e.status = 'Maintenance' WHERE e.id = ?1")
    void scheduleMaintenanceForEquipment(Integer equipmentId);
}
