package repository;

import model.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User getUserByEmailAndPassword(String email, String password);
}
