package spring.boot.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RentalTransaction")
public class RentalTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Customer ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer customerId;

    @NotNull(message = "Equipment ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer equipmentId;

    @NotNull(message = "Driver ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer driverId;

    @NotNull(message = "Rental start date must not be null")
    @Column(columnDefinition = "date not null")
    private LocalDate startDate;

    @NotNull(message = "Rental end date must not be null")
    @Column(columnDefinition = "date not null")
    private LocalDate endDate;

    @NotNull(message = "Total cost must not be null")
    @Positive(message = "Total cost must be positive")
    @Column(columnDefinition = "double not null")
    private double totalCost;
}
