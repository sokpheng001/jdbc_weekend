package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;

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
}
