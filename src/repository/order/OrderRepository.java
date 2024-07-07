package repository.order;

import model.user.UserOrder;
import model.user.UserOrderDetail;

import java.util.List;

public interface OrderRepository {
    int makeOrder(UserOrder userOrder);
    List<UserOrderDetail> userOrder();
}
