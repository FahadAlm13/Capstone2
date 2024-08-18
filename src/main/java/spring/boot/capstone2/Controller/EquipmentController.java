package spring.boot.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.capstone2.Model.Equipment;
import spring.boot.capstone2.Service.EquipmentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;


    @GetMapping("/get")
    public ResponseEntity getAllEquipment() {
        return ResponseEntity.status(200).body(equipmentService.getAllEquipment());
    }

    @PostMapping("/add")
    public ResponseEntity addEquipment(@Valid @RequestBody Equipment equipment, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        equipmentService.addEquipment(equipment);
        return ResponseEntity.status(200).body("Equipment added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEquipment(@Valid @RequestBody Equipment equipment, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        equipmentService.updateEquipment(equipment, id);
        return ResponseEntity.status(200).body("Equipment updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEquipment(@PathVariable Integer id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.status(200).body("Equipment deleted");
    }
    @GetMapping("/getByType/{type}")
    public ResponseEntity getEquipmentByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(equipmentService.getEquipmentByType(type));
    }

    @GetMapping("/getByStatus/{status}")
    public ResponseEntity getEquipmentByStatus(@PathVariable String status) {
        return ResponseEntity.status(200).body(equipmentService.getEquipmentByStatus(status));
    }

    @GetMapping("/getAvailableEquipment")
    public ResponseEntity getAvailableEquipment() {
        return ResponseEntity.status(200).body(equipmentService.getAvailableEquipment());
    }
    @PutMapping("/updateEquipmentStatus")
    public ResponseEntity updateEquipmentStatus(@RequestParam Integer equipmentId, @RequestParam String status) {
        equipmentService.updateEquipmentStatus(equipmentId, status);
        return ResponseEntity.status(200).body("Equipment status updated successfully");
    }
    @PostMapping("/scheduleMaintenance")
    public ResponseEntity scheduleMaintenanceForEquipment(@RequestParam Integer equipmentId) {
        equipmentService.scheduleMaintenanceForEquipment(equipmentId);
        return ResponseEntity.status(200).body("Maintenance scheduled successfully");
    }
}
