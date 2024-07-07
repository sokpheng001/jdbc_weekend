package repository.user;

import model.user.UpdateUserDto;
import model.user.User;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User login(String email, String password);
    int updateUserById(Integer id, UpdateUserDto updateUserDto);
    int insertNewUser(User user);
    int deleteUserById(int id);
}
