package repository;

import model.UpdateUserDto;
import model.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User login(String email, String password);
    int updateUserById(Integer id, UpdateUserDto updateUserDto);
}
