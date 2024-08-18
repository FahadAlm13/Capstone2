package spring.boot.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Points must not be null")
    @PositiveOrZero(message = "Points must be zero or positive")
    @Column(columnDefinition = "int not null default 0")
    private int points;

    @NotNull(message = "Driver ID must not be null")
    @Column(columnDefinition = "int not null")
    private Integer driverId;
}
