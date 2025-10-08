import java.util.List;

public class Main {
    static void main() {
        List<Order> testOrders = List.of(
                new Order(1, "confirmed"),
                new Order(2, "shipped"),
                new Order(3, "pending"),
                new Order(4, "confirmed"),
                new Order(5, "shipped")
        );

        OrderProcessingFacade processor = new OrderProcessingFacade(testOrders);

        System.out.println("Начинаем обработку заказов...\n");
        processor.handleOrders();
        System.out.println("\nОбработка заказов завершена.");
    }
}
