package spring.boot.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.capstone2.Model.Credit;
import spring.boot.capstone2.Service.CreditService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit")
public class CreditController {
    private final CreditService creditService;


    @GetMapping("/get")
    public ResponseEntity getAllCredits() {
        return ResponseEntity.status(200).body(creditService.getAllCredits());
    }

    @PostMapping("/add")
    public ResponseEntity addCredit(@Valid @RequestBody Credit credit, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        creditService.addCredit(credit);
        return ResponseEntity.status(201).body("Credit added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCredit(@Valid @RequestBody Credit credit, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        creditService.updateCredit(credit, id);
        return ResponseEntity.status(201).body("Credit updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCredit(@PathVariable Integer id) {
        creditService.deleteCredit(id);
        return ResponseEntity.status(201).body("Credit deleted");
    }
    @GetMapping("/getByDriver/{driverId}")
    public ResponseEntity getCreditsByDriverId(@PathVariable Integer driverId) {
        return ResponseEntity.status(200).body(creditService.getCreditsByDriverId(driverId));
    }

    @PutMapping("/addPoints/{driverId}")
    public ResponseEntity addPoints(@PathVariable Integer driverId, @RequestParam int points) {
        creditService.addPoints(driverId, points);
        return ResponseEntity.status(200).body("Points added to driver's credit");
    }

    @PutMapping("/resetPoints/{driverId}")
    public ResponseEntity resetPoints(@PathVariable Integer driverId) {
        creditService.resetPoints(driverId);
        return ResponseEntity.status(200).body("Driver's points reset to zero");
    }
    @PostMapping("/transferCredit")
    public ResponseEntity transferCreditBetweenDrivers(@RequestParam Integer fromDriverId, @RequestParam Integer toDriverId, @RequestParam int points) {
        creditService.transferCreditBetweenDrivers(fromDriverId, toDriverId, points);
        return ResponseEntity.status(200).body("Credit transferred successfully");
    }
}
