package spring.boot.capstone2.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.Driver;
import spring.boot.capstone2.Model.Review;
import spring.boot.capstone2.Model.User;
import spring.boot.capstone2.Repository.DriverRepository;
import spring.boot.capstone2.Repository.ReviewRepository;
import spring.boot.capstone2.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }
    //22
    public List<Review> getReviewsByDriverId(Integer driverId) {
        return reviewRepository.findReviewsByDriverId(driverId);
    }
    //23
    public List<Review> getReviewsByEquipmentId(Integer equipmentId) {
        return reviewRepository.findReviewsByEquipmentId(equipmentId);
    }
    //21
    public void addReview(Review review) {
        User user = userRepository.findUsersById(review.getUserId());
        if (user == null || !"Customer".equalsIgnoreCase(user.getRole())) {
            throw new ApiException("User not found ");
        }
             reviewRepository.save(review);

        if (review.getDriverId() != null && review.getRating() == 1) {
            Driver driver = driverRepository.findDriverById(review.getDriverId());

            if (driver == null) {
                throw new ApiException("Driver not found");
            }

            driver.setWarnings(driver.getWarnings() + 1);

            if (driver.getWarnings() >= 3) {
                driver.setBlacklisted(true);
            }

            driverRepository.save(driver);
        }
    }

    public void deleteReview(Integer reviewId, Integer adminId) {
        User admin = userRepository.findUsersById(adminId);
        if (admin == null || !"Admin".equalsIgnoreCase(admin.getRole())) {
            throw new ApiException("Only admins can delete reviews");
        }
        Review review = reviewRepository.findReviewById(reviewId);
        if (review == null) {
            throw new ApiException("Review not found");
        }
        reviewRepository.delete(review);
    }
    //24
    public List<Review> getReviewsByUserId(Integer userId) {
        return reviewRepository.findReviewsByUserId(userId);
    }


//    public void handleLowRating(Integer reviewId) {
//        Review review = reviewRepository.findReviewById(reviewId);
//        if (review == null) {
//            throw new ApiException("Review not found");
//        }
//
//        if (review.getDriverId() != null && review.getRating() == 1) {
//            Driver driver = driverRepository.findDriverById(review.getDriverId());
//
//            if (driver == null) {
//                throw new ApiException("Driver not found");
//            }
//
//            driver.setWarnings(driver.getWarnings() + 1);
//
//            if (driver.getWarnings() >= 3) {
//                driver.setBlacklisted(true);
//            }
//
//            driverRepository.save(driver);
//        }
//    }
}
