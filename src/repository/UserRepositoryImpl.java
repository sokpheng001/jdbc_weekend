package repository;

import model.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return null;
    }
}
