package spring.boot.capstone2.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.Equipment;
import spring.boot.capstone2.Repository.EquipmentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }

    public void addEquipment(Equipment equipment) {
        equipmentRepository.save(equipment);
    }

    public void updateEquipment(Equipment equipment, Integer id) {
        Equipment equip = equipmentRepository.findEquipmentById(id);
        if (equip == null) {
            throw new ApiException("Equipment not found");
        }
        equip.setName(equipment.getName());
        equip.setType(equipment.getType());
        equip.setStatus(equipment.getStatus());
        equip.setDailyRate(equipment.getDailyRate());
    }

    public void deleteEquipment(Integer id) {
        Equipment equipment = equipmentRepository.findEquipmentById(id);
        if (equipment == null) {
            throw new ApiException("Equipment not found");
        }
        equipmentRepository.delete(equipment);
    }

    //4
    public List<Equipment> getEquipmentByType(String type) {
        return equipmentRepository.findByType(type);
    }

    //5
    public List<Equipment> getEquipmentByStatus(String status) {
        return equipmentRepository.findByStatus(status);
    }

    //6
    public List<Equipment> getAvailableEquipment() {
        return equipmentRepository.findAvailableEquipment();
    }

    //17
    public void updateEquipmentStatus(Integer equipmentId, String status) {
        Equipment equipment = equipmentRepository.findEquipmentById(equipmentId);
        if (equipment == null) {
            throw new ApiException("Equipment not found");
        }

        equipmentRepository.updateEquipmentStatus(equipmentId, status);
    }
    //20
    public void scheduleMaintenanceForEquipment(Integer equipmentId) {
        Equipment equipment = equipmentRepository.findEquipmentById(equipmentId);
        if (equipment == null) {
            throw new ApiException("Equipment not found");
        }

        equipmentRepository.scheduleMaintenanceForEquipment(equipmentId);
    }
}
