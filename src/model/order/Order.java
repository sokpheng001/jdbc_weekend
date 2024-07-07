package model.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.product.Product;
import model.user.User;

import java.sql.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private String orderName;
    private Date orderDate;
    // Many to one, one order can be belonged to one user
    private User user;
//    Many to Many
    List<Product> productList;
}
