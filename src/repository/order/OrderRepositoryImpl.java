package repository.order;

import model.user.UserOrder;
import model.user.UserOrderDetail;
import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{

    @Override
    public int makeOrder(UserOrder userOrder) {
        String orderSql = """
                INSERT INTO "orders"
                VALUES(?,?,?,?)
                """;
        try(Connection connection = DBConnection.connection();
            PreparedStatement preparedStatement = connection.prepareStatement(orderSql);
                ){
            preparedStatement.setInt(1,userOrder.orderId());
            preparedStatement.setString(2,userOrder.orderName());
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(4,userOrder.userId());

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0){
                System.out.println("User with ID " + userOrder.userId() + " ordered successfully");
            }else {
                System.out.println("Cannot Order");
            }
        }catch (Exception exception){
            System.out.println("Problem during insert into table order:" + exception.getMessage());
        }
//        insert into order_product
        String orderProductSql = """
                INSERT INTO "order_product"
                VALUES(?,?)
                """;
        try(
                Connection connection = DBConnection.connection();
                PreparedStatement preparedStatement = connection.prepareStatement(orderProductSql);
                ){
            preparedStatement.setInt(1,userOrder.orderId());
            preparedStatement.setInt(2,userOrder.productId());

            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected>0) {
                System.out.println("Order Product successfully");
                return rowAffected;
            }
        }catch (Exception exception){
            System.out.println( "Problem during insert into table order_product: " + exception.getMessage());
        }
        return 0;
    }

    @Override
    public List<UserOrderDetail> userOrder() {
        String sql = """
                SELECT o.order_date, u.user_name, p.product_name
                FROM order_product AS op
                INNER JOIN public.orders AS o ON op.order_id = o.order_id
                INNER JOIN public.products AS p ON op.product_id = p.p_id
                INNER JOIN public.users AS u ON o.user_id = u.id;
                """;
        try(
                Connection connection = DBConnection.connection();
                Statement statement  =connection.createStatement();
                ){
            ResultSet resultSet = statement.executeQuery(sql);
            List<UserOrderDetail> orderDetailList = new ArrayList<>();
            while (resultSet.next()){
                UserOrderDetail userOrderDetail
                         = UserOrderDetail.builder()
                        .orderedDate(resultSet.getDate("order_date"))
                        .productName(resultSet.getString("product_name"))
                        .userName(resultSet.getString("user_name"))
                        .build();
                orderDetailList.add(userOrderDetail);
            }
            return orderDetailList;

        }catch (Exception exception){
            System.out.println("Problem during get order detail:" + exception.getMessage());
        }
        return List.of();
    }
}
