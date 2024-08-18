package spring.boot.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "type in('Wheel','Digger','flip-flop')")
@Check(constraints = "status in('available','rented','Maintenance')")
@Table(name = "Equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String name;

    @NotEmpty(message = "Type must not be empty")
    @Pattern(regexp = "^(Wheel|Digger|flip-flop)$", message = "Type must be one of 'Wheel', 'Digger', or 'flip-flop'")
    @Column(columnDefinition = "varchar(20) not null")
    private String type;

    @NotEmpty(message = "Status must not be empty")
    @Pattern(regexp ="^(Available|Rented|Maintenance)$", message = "Status must be either 'Available', 'Rented', or 'Maintenance'")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @NotNull(message = "Daily Rate must not be empty")
    @Positive(message = "Daily Rate must be a positive number")
    @Column(columnDefinition = "double not null")
    private double dailyRate;
}
