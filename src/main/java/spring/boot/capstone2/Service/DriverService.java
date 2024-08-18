package spring.boot.capstone2.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.Driver;
import spring.boot.capstone2.Repository.CreditRepository;
import spring.boot.capstone2.Repository.DriverRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DriverService {

    private final DriverRepository driverRepository;
    private final CreditRepository creditRepository;
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public void addDriver(Driver driver) {
        driverRepository.save(driver);
    }

    public void updateDriver(Driver driver, Integer id) {
        Driver d = driverRepository.findDriverById(id);
        if (d == null) {
            throw new ApiException("Driver not found");
        }
        d.setName(driver.getName());
        d.setExperience(driver.getExperience());
        d.setLicenseNumber(driver.getLicenseNumber());

        driverRepository.save(d);
    }

    public void deleteDriver(Integer id) {
        Driver d = driverRepository.findDriverById(id);
        if (d == null) {
            throw new ApiException("Driver not found");
        }
        driverRepository.delete(d);
    }
    //7
    public List<Driver> getDriversByExperience(int years) {
        return driverRepository.findByExperienceGreaterThanEqual(years);
    }
    //8
    public List<Driver> getTopDrivers() {
        return driverRepository.findTopDrivers();
    }
    //19
    public void updateDriverExperience(Integer driverId, int experience) {
        Driver driver = driverRepository.findDriverById(driverId);
        if (driver == null) {
            throw new ApiException("Driver not found");
        }
        driverRepository.updateDriverExperience(driverId, experience);
    }
}
