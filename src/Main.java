
import repository.order.OrderRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        new OrderRepositoryImpl()
                .userOrder()
                .forEach(System.out::println);
    }
}