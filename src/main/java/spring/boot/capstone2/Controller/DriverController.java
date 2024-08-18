package spring.boot.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.capstone2.Model.Driver;
import spring.boot.capstone2.Service.DriverService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/driver")
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/get")
    public ResponseEntity getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }
    @PostMapping("/add")
    public ResponseEntity addDriver(@Valid @RequestBody Driver driver, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        driverService.addDriver(driver);
        return ResponseEntity.status(200).body("Driver added successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateDriver(@Valid @RequestBody Driver driver,@PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        driverService.updateDriver(driver,id);
        return ResponseEntity.status(200).body("Driver updated successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteDriver(@PathVariable Integer id) {
        driverService.deleteDriver(id);
        return ResponseEntity.status(200).body("Driver deleted successfully");
    }
    @GetMapping("/getDriverByExperience/{experience}")
    public ResponseEntity getDriversByExperience(@PathVariable Integer experience) {
        return ResponseEntity.status(200).body(driverService.getDriversByExperience(experience));
    }
    @GetMapping("/getTopDrivers")
    public ResponseEntity getTopDrivers(){
        return ResponseEntity.status(200).body(driverService.getTopDrivers());
    }
    @PutMapping("/updateExperience")
    public ResponseEntity updateDriverExperience(@RequestParam Integer driverId, @RequestParam int experience) {
        driverService.updateDriverExperience(driverId, experience);
        return ResponseEntity.status(200).body("Driver experience updated successfully");
    }

}

