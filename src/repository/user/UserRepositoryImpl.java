package repository.user;

import model.user.UpdateUserDto;
import model.user.User;
import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    @Override
    public List<User> getAllUsers() {
        String sql = """
                SELECT * FROM users
                """;
        try(
                Connection connection = DBConnection.connection();
                Statement statement = connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(sql);
            List<User>  userList = new ArrayList<>();
            while (resultSet.next()){
                userList.add(
                        new User(
                                resultSet.getInt("id"),
                                resultSet.getString("user_name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),null
                        ));}
            return userList;
        }catch (Exception exception){
            System.out.println("Problem during read all users: " + exception.getMessage());
        }
        return List.of();
    }

    @Override
    public User getUserById(Integer id) {
        String sql = """
                SELECT * FROM users
                WHERE id = ?
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
;                ) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        null
                );
            }
            if (user==null){
                System.out.println("User not found");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Problem during get all users: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public User login(String email, String password) {
        String sql = """
                SELECT * FROM users 
                WHERE email = ? AND password = ?
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)
                ;                ) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()){
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("user_name"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        null
                );
            }
            if (user==null){
                System.out.println("[+] Login failed");
            }else{
                System.out.println("[!] Login successfully");
            }
            return user;
        }catch (Exception exception){
            System.out.println("[!] Problem during get all users: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public int updateUserById(Integer id,UpdateUserDto updateUserDto) {
        String sql = """
                UPDATE "users"
                SET user_name = ?, email = ? , password = ?
                WHERE id = ?
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
            preparedStatement.setString(1, updateUserDto.userName());
            preparedStatement.setString(2, updateUserDto.email());
            preparedStatement.setString(3,updateUserDto.password());
            preparedStatement.setInt(4,id);

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Update sucessfully");
            }else {
                System.out.println("Update failed");
            }
            return rowAffected;


        }catch (Exception exception){
            System.out.println("Problem during get user by ID: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public int insertNewUser(User user) {
        String sql = """
                INSERT INTO "users"
                VALUES(?,?,?,?)
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement  =connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2,user.getUserName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
             int rowAffected = preparedStatement.executeUpdate();
             if(rowAffected>0){
                 System.out.println("Insert new user successfully");
                 return rowAffected;
             }else {
                 System.out.println("Insert new User failed");
             }
        }catch (Exception exception){
            System.out.println("Problem during insert new User: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public int deleteUserById(int id) {
        String sql = """
                DELETE FROM "users"
                WHERE id = ?
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement  = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1,id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Delete user successfully");
                return rowAffected;
            }else {
                System.out.println("Delete user failed");
            }

        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        return 0;
    }

}
