package repository.product;

import model.product.Product;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository{
    @Override
    public List<Product> listAllProducts() {
        List<Product> productList = new ArrayList<>();
        String sql = """
                     SELECT * FROM products
                     """;
        try(
                Connection connection = DBConnection.connection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                ){
            while (resultSet.next()){
                Product product = new Product();
                product.setId(resultSet.getInt("p_id"));
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
                Connection connection =DBConnection.connection();
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
                Connection connection = DBConnection.connection();
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
    public List<Product> getProductByName(String name){
        String sql = """
                SELECT * FROM "products"
                WHERE product_name ILIKE ?
                """;

        try(
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/java_weekend",
                        "postgres",
                        "123"
                );
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ){
            preparedStatement.setString(1,"%" + name + "%");// implement dynamic search
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(
                        resultSet.getInt("p_id"),
                        resultSet.getString("product_name"),
                        resultSet.getDate("released_date"),
                        resultSet.getBigDecimal("price"),null);
                productList.add(product);
            }
            return productList;

        }catch (Exception exception){
            System.out.println("Problem during get product by name: " + exception.getMessage());
        }
        return List.of();
    }
    @Override
    public int deleteProductById(int id) {
        String sql = """
                DELETE FROM "products" 
                WHERE p_id = ?
                """;
        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/java_weekend",
                "postgres",
                "123");
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setInt(1,id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Delete product successfully");
            }else {
                System.out.println("Cannot delete product or product not found");
            }
            return rowAffected;
        }catch (Exception exception){
            System.out.println("Problem during delete product by ID: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public int updateProductById(int id, Product product) {
        String sql = """
                UPDATE "products"
                SET product_name = ?,
                    released_date = ?,
                    price = ? 
                WHERE p_id = ?
                """;
        try(Connection connection = DBConnection.connection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDate(2,product.getReleasedDate());
            preparedStatement.setBigDecimal(3,product.getPrice());
            preparedStatement.setInt(4,id);
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("Update Product Successfully");
            }else {
                System.out.println("Update failed");
            }
            return rowAffected;
        }catch (Exception exception){
            System.out.println("Problem during update product data: "
                    + exception.getMessage());
        }
        return 0;
    }
}
