package spring.boot.capstone2.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.capstone2.Model.Review;
import spring.boot.capstone2.Service.ReviewService;



@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get")
    public ResponseEntity getAllReviews() {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
    @GetMapping("/driver/{driverId}")
    public ResponseEntity getReviewsByDriverId(@PathVariable Integer driverId) {
        return ResponseEntity.status(200).body(reviewService.getReviewsByDriverId(driverId));
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity getReviewsByEquipmentId(@PathVariable Integer equipmentId) {
        return ResponseEntity.status(200).body(reviewService.getReviewsByEquipmentId(equipmentId));
    }

    @PostMapping("/add")
    public ResponseEntity addReview(@Valid @RequestBody Review review, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        reviewService.addReview(review);
        return ResponseEntity.status(200).body("Review added successfully");
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable Integer reviewId, @RequestParam Integer adminId) {
        reviewService.deleteReview(reviewId, adminId);
        return ResponseEntity.status(200).body("Review deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getReviewsByUserId(@PathVariable Integer userId) {
        return ResponseEntity.status(200).body(reviewService.getReviewsByUserId(userId));
    }

//    @PostMapping("/handleLowRating/{reviewId}")
//    public ResponseEntity handleLowRating(@PathVariable Integer reviewId) {
//        reviewService.handleLowRating(reviewId);
//        return ResponseEntity.status(200).body("Low rating handled");
//    }
}
