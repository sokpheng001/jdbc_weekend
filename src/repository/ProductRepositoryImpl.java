package repository;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public List<Product> listAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = """
                     SELECT * FROM products
                     """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/java_weekend",
                        "postgres",
                        "123"
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setReleasedDate(resultSet.getDate("released_date"));
                product.setPrice(resultSet.getBigDecimal("price"));
                productList.add(product);
            }
        }catch (SQLException sqlException){
            System.out.println("Problem during get all products from database: " + sqlException.getMessage());
        }
        return productList;
    }

    @Override
    public List<Product> getProductById(Integer id) {
        List<Product> productList = new ArrayList<>();
        String sql = """
                SELECT * FROM products WHERE id = ?;
                """;
        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/java_weekend",
                        "postgres",
                        "123"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("product_name"));
                product.setReleasedDate(resultSet.getDate("released_date"));
                product.setPrice(resultSet.getBigDecimal("price"));
                productList.add(product);
            }
        }catch (SQLException sqlException){
            System.out.println("Problem during get product by ID: " + sqlException.getMessage());
        }
        return productList;
    }

    @Override
    public int insertNewProduct(Product product) {
        String sql = """
                INSERT INTO products 
                VALUES(?,?,?,?)
                """;
        try (
                Connection connection =DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/java_weekend",
                        "postgres",
                        "123"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setInt(1,product.getId());
            preparedStatement.setString(2,product.getProductName());
            preparedStatement.setDate(3,product.getReleasedDate());
            preparedStatement.setBigDecimal(4,product.getPrice());

            int rowAffected = preparedStatement.executeUpdate();
            return rowAffected;
        }catch (SQLException sqlException){
            System.out.println("Problem during Insert new Product: " + sqlException.getMessage());
        }
        return 0;
    }

    @Override
    public List<Product> getProductByName(String name) {
        return List.of();
    }
}
