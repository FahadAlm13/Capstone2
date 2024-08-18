package spring.boot.capstone2.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.Driver;
import spring.boot.capstone2.Model.Equipment;
import spring.boot.capstone2.Model.RentalTransaction;
import spring.boot.capstone2.Model.User;
import spring.boot.capstone2.Repository.DriverRepository;
import spring.boot.capstone2.Repository.EquipmentRepository;
import spring.boot.capstone2.Repository.RentalTransactionRepository;
import spring.boot.capstone2.Repository.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class RentalTransactionService {
    private RentalTransactionRepository rentalTransactionRepository;
    private final UserRepository userRepository;
    private final EquipmentRepository equipmentRepository;
    private final DriverRepository driverRepository;

    public List<RentalTransaction> getAllRentalTransactions() {
        return rentalTransactionRepository.findAll();
    }

    public void addRentalTransaction(RentalTransaction rentalTransaction) {
        User u = userRepository.findUsersById(rentalTransaction.getCustomerId());
        Equipment e = equipmentRepository.findEquipmentById(rentalTransaction.getEquipmentId());
        Driver d = driverRepository.findDriverById(rentalTransaction.getDriverId());

        if (u == null) {
            throw new ApiException("User not found");
        }
        if (e == null) {
            throw new ApiException("Equipment not found");
        }
        if (d == null) {
            throw new ApiException("Driver not found");
        }
        double rentalDays = rentalTransaction.getEndDate().toEpochDay() - rentalTransaction.getStartDate().toEpochDay();
        if (rentalDays < 1) {
            rentalDays = 1;
        }
        double totalCost = rentalDays * e.getDailyRate();
        if (u.getIsPrime()) {
            totalCost *= 0.9;
        }
        rentalTransaction.setTotalCost(totalCost);
        rentalTransactionRepository.save(rentalTransaction);
    }

    public void updateRentalTransaction(RentalTransaction rentalTransaction, Integer id) {
        RentalTransaction rt = rentalTransactionRepository.findRentalTransactionById(id);
        User u = userRepository.findUsersById(rentalTransaction.getId());
        Equipment e = equipmentRepository.findEquipmentById(rentalTransaction.getEquipmentId());
        Driver d = driverRepository.findDriverById(rentalTransaction.getDriverId());
        if (rt == null) {
            throw new ApiException("Rental Transaction not found");
        }
        if (u == null) {
            throw new ApiException("User not found");
        }
        if (e == null) {
            throw new ApiException("Equipment not found");
        }
        if (d == null) {
            throw new ApiException("Driver not found");
        }

        double rentalDays = rentalTransaction.getEndDate().toEpochDay() - rentalTransaction.getStartDate().toEpochDay();
        if (rentalDays < 1) {
            rentalDays = 1;
        }
        double totalCost = rentalDays * e.getDailyRate();
        if (u.getIsPrime()) {
            totalCost *= 0.9;
        }

        rt.setCustomerId(rentalTransaction.getCustomerId());
        rt.setEquipmentId(rentalTransaction.getEquipmentId());
        rt.setDriverId(rentalTransaction.getDriverId());
        rt.setStartDate(rentalTransaction.getStartDate());
        rt.setEndDate(rentalTransaction.getEndDate());

        rt.setTotalCost(totalCost);

        rentalTransactionRepository.save(rt);
    }

    public void deleteRentalTransaction(Integer id) {
        RentalTransaction rt = rentalTransactionRepository.findRentalTransactionById(id);
        if (rt == null) {
            throw new ApiException("Rental Transaction not found");
        }
        rentalTransactionRepository.delete(rt);
    }

    //9
    public List<RentalTransaction> getTransactionsByCustomerId(Integer customerId) {
        return rentalTransactionRepository.findByCustomerId(customerId);
    }

    //10
    public List<RentalTransaction> getTransactionsByEquipmentId(Integer equipmentId) {
        return rentalTransactionRepository.findByEquipmentId(equipmentId);
    }
    //14
    public void extendRentalDuration(Integer rentalTransactionId, int additionalDays) {
        RentalTransaction rentalTransaction = rentalTransactionRepository.findRentalTransactionById(rentalTransactionId);
        if (rentalTransaction == null) {
            throw new ApiException("Rental Transaction not found");
        }

        // Calculate the new end date by adding the additional days
        LocalDate newEndDate = rentalTransaction.getEndDate().plusDays(additionalDays);

        rentalTransactionRepository.extendRentalDuration(rentalTransactionId, newEndDate);

        double rentalDays = newEndDate.toEpochDay() - rentalTransaction.getStartDate().toEpochDay();
        if (rentalDays < 1) {
            rentalDays = 1;
        }
        double totalCost = rentalDays * rentalTransaction.getTotalCost() / (rentalTransaction.getEndDate().toEpochDay() - rentalTransaction.getStartDate().toEpochDay());
        rentalTransaction.setTotalCost(totalCost);
        rentalTransaction.setEndDate(newEndDate);

        rentalTransactionRepository.save(rentalTransaction);
    }
//    public void extendRentalDuration(Integer rentalTransactionId, LocalDate newEndDate) {
//        RentalTransaction rentalTransaction = rentalTransactionRepository.findRentalTransactionById(rentalTransactionId);
//        if (rentalTransaction == null) {
//            throw new ApiException("Rental Transaction not found");
//        }
//
//        rentalTransactionRepository.extendRentalDuration(rentalTransactionId, newEndDate);
//
//        double rentalDays = newEndDate.toEpochDay() - rentalTransaction.getStartDate().toEpochDay();
//        if (rentalDays < 1) {
//            rentalDays = 1;
//        }
//        double totalCost = rentalDays * rentalTransaction.getTotalCost() / (rentalTransaction.getEndDate().toEpochDay() - rentalTransaction.getStartDate().toEpochDay());
//        rentalTransaction.setTotalCost(totalCost);
//        rentalTransaction.setEndDate(newEndDate);
//
//        rentalTransactionRepository.save(rentalTransaction);
//    }
    //15
    public void applyDiscountToRental(Integer rentalTransactionId, double discountPercentage) {
        RentalTransaction rentalTransaction = rentalTransactionRepository.findRentalTransactionById(rentalTransactionId);
        if (rentalTransaction == null) {
            throw new ApiException("Rental Transaction not found");
        }

        double discountAmount = rentalTransaction.getTotalCost() * (discountPercentage / 100);
        rentalTransaction.setTotalCost(rentalTransaction.getTotalCost() - discountAmount);

        rentalTransactionRepository.save(rentalTransaction);
    }
    //16
    public void assignDriverToRental(Integer rentalTransactionId, Integer driverId) {
        RentalTransaction rentalTransaction = rentalTransactionRepository.findRentalTransactionById(rentalTransactionId);
        if (rentalTransaction == null) {
            throw new ApiException("Rental Transaction not found");
        }

        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }

        rentalTransactionRepository.assignDriverToRental(rentalTransactionId, driverId);
    }
}
