package spring.boot.capstone2.Model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "User ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer userId;

    @NotNull(message = "Driver ID or Equipment ID must not be null")
    @Column(columnDefinition = "int")
    private Integer driverId;

    @NotNull(message = "Driver ID or Equipment ID must not be null")
    @Column(columnDefinition = "int")
    private Integer equipmentId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(columnDefinition = "int not null")
    private int rating;

    @NotEmpty(message = "Review content must not be empty")
    @Column(columnDefinition = "text not null")
    private String content;

}