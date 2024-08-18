package spring.boot.capstone2.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.capstone2.Api.ApiException;
import spring.boot.capstone2.Model.User;
import spring.boot.capstone2.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user, Integer id) {
        User updateUser = userRepository.findUsersById(id);
        if (updateUser == null) {
            throw new ApiException("User not found");
        }
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setEmail(user.getEmail());
        updateUser.setRole(user.getRole());
        updateUser.setBalance(user.getBalance());
        updateUser.setIsPrime(user.getIsPrime());

        userRepository.save(updateUser);
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findUsersById(id);
        if (user == null) {
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    //1
    public List<User> getUsersByPrimeStatus(boolean isPrime) {
        return userRepository.findByIsPrime(isPrime);
    }

    //2
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ApiException("User not found");
        }
        return user;
    }

    //3
    public void subscribePrime(Integer id) {
        User user = userRepository.findUsersById(id);
        if (user == null || user.getRole().equalsIgnoreCase("Admin")) {
            throw new ApiException("User not eligible for prime subscription");
        }
        if (user.getBalance() < 100) {
            throw new ApiException("User not have enough balance for prime subscription");
        }
        user.setIsPrime(true);
        user.setBalance(user.getBalance() - 100); // Deduct prime subscription fee
        userRepository.save(user);
    }

}
