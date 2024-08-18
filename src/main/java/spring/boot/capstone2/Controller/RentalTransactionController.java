package spring.boot.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.capstone2.Model.RentalTransaction;
import spring.boot.capstone2.Service.RentalTransactionService;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/RentalTransaction")
public class RentalTransactionController {
    private final RentalTransactionService rentalTransactionService;

    @GetMapping("/get")
    public ResponseEntity getAllRentalTransactions() {
        return ResponseEntity.status(200).body(rentalTransactionService.getAllRentalTransactions());
    }

    @PostMapping("/add")
    public ResponseEntity addRentalTransaction(@Valid @RequestBody RentalTransaction rentalTransaction, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        rentalTransactionService.addRentalTransaction(rentalTransaction);
        return ResponseEntity.status(200).body("Rental Transaction added successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateRentalTransaction(@Valid @RequestBody RentalTransaction rentalTransaction, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        rentalTransactionService.updateRentalTransaction(rentalTransaction, id);
        return ResponseEntity.status(200).body("Rental Transaction updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteRentalTransaction(@PathVariable Integer id) {
        rentalTransactionService.deleteRentalTransaction(id);
        return ResponseEntity.status(200).body("Rental Transaction deleted successfully");
    }
    @GetMapping("/getByCustomer/{customerId}")
    public ResponseEntity getTransactionsByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.status(200).body(rentalTransactionService.getTransactionsByCustomerId(customerId));
    }

    @GetMapping("/getByEquipment/{equipmentId}")
    public ResponseEntity getTransactionsByEquipmentId(@PathVariable Integer equipmentId) {
        return ResponseEntity.status(200).body(rentalTransactionService.getTransactionsByEquipmentId(equipmentId));
    }
    @PostMapping("/extendRental")
    public ResponseEntity extendRentalDuration(@RequestParam Integer rentalTransactionId, @RequestParam int additionalDays) {
        rentalTransactionService.extendRentalDuration(rentalTransactionId, additionalDays);
        return ResponseEntity.status(200).body("Rental duration extended successfully");
    }
    @PostMapping("/applyDiscount")
    public ResponseEntity applyDiscountToRental(@RequestParam Integer rentalTransactionId, @RequestParam double discountPercentage) {
        rentalTransactionService.applyDiscountToRental(rentalTransactionId, discountPercentage);
        return ResponseEntity.status(200).body("Discount applied successfully");
    }
    @PutMapping("/assignDriver")
    public ResponseEntity assignDriverToRental(@RequestParam Integer rentalTransactionId, @RequestParam Integer driverId) {
        rentalTransactionService.assignDriverToRental(rentalTransactionId, driverId);
        return ResponseEntity.status(200).body("Driver assigned successfully");
    }
}
