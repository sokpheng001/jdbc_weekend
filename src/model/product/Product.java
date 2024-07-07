package model.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import model.order.Order;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String productName;
    private Date releasedDate;
    private BigDecimal price;
//    Many to Many
    List<Order> orderList;
}
