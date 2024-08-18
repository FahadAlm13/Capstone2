package spring.boot.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Driver name must not be null")
    @Size(min = 3, message = "Driver name must be at least 3 characters long")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    @NotNull(message = "License number must not be null")
    @Size(min = 5, message = "License number must be at least 5 characters long")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String licenseNumber;

    @NotNull(message = "Experience must not be null")
    @PositiveOrZero(message = "Experience must be zero or positive")
    @Column(columnDefinition = "int not null default 0")
    private int experience;

    @Column(columnDefinition = "int not null default 0")
    private int warnings;

    @Column(columnDefinition = "boolean not null default false")
    private boolean isBlacklisted;
}
