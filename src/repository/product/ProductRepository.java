package repository.product;

import model.product.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> listAllProducts();
    List<Product> getProductById(Integer id);
    int insertNewProduct(Product product);
    List<Product> getProductByName(String name);
    int deleteProductById(int id);
    int updateProductById(int id, Product product);
}
