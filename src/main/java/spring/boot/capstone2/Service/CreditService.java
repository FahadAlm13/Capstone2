package spring.boot.capstone2.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.Credit;
import spring.boot.capstone2.Model.Driver;
import spring.boot.capstone2.Repository.CreditRepository;
import spring.boot.capstone2.Repository.DriverRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CreditService {
    private final DriverRepository driverRepository;
    private CreditRepository creditRepository;

    public List<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    public void addCredit(Credit credit) {
        Driver d = driverRepository.findDriverById(credit.getDriverId());
        if (d == null) {
            throw new ApiException("Driver not found");
        }
        creditRepository.save(credit);
    }

    public void updateCredit(Credit credit, Integer id) {
        Credit c = creditRepository.findCreditById(id);
        Driver d = driverRepository.findDriverById(credit.getDriverId());
        if (c == null) {
            throw new ApiException("Credit not found");
        }
        if (d == null) {
            throw new ApiException("Driver not found");
        }

        c.setPoints(credit.getPoints());
        c.setDriverId(credit.getDriverId());
        creditRepository.save(c);
    }

    public void deleteCredit(Integer id) {
        Credit c = creditRepository.findCreditById(id);
        if (c == null) {
            throw new ApiException("Credit not found");
        }
        creditRepository.delete(c);
    }
    //11
    public List<Credit> getCreditsByDriverId(Integer driverId) {
        return creditRepository.findByDriverId(driverId);
    }
    //12
    public void addPoints(Integer driverId, int points) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        creditRepository.addPoints(driverId, points);
    }
    //13
    public void resetPoints(Integer driverId) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        creditRepository.resetPoints(driverId);
    }
    //18
    public void transferCreditBetweenDrivers(Integer fromDriverId, Integer toDriverId, int points) {
        Credit fromCredit = creditRepository.findCreditByDriverId(fromDriverId);
        Credit toCredit = creditRepository.findCreditByDriverId(toDriverId);

        if (fromCredit == null || toCredit == null) {
            throw new ApiException("Driver not found");
        }

        if (fromCredit.getPoints() < points) {
            throw new ApiException("Insufficient points");
        }

        fromCredit.setPoints(fromCredit.getPoints() - points);
        toCredit.setPoints(toCredit.getPoints() + points);

        creditRepository.save(fromCredit);
        creditRepository.save(toCredit);
    }
}
