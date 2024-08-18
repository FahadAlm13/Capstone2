package spring.boot.capstone2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Check(constraints = "role in('Admin','Customer')")
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 4, message = "Username must have at least 4 characters")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String password;

    @Email
    @NotEmpty(message = "Email cannot be empty")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "Role cannot be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @Positive(message = "Balance must be a positive number")
    @Column(columnDefinition = "double not null")
    private double balance;

    @Column(columnDefinition = "boolean not null")
    private Boolean isPrime = false;

}
