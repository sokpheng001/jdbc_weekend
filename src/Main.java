import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        new ProductRepositoryImpl()
                .insertNewProduct(
                        new Product(4,"មីគោក", Date.valueOf(LocalDate.now()),new BigDecimal("4.5")));
    }
}