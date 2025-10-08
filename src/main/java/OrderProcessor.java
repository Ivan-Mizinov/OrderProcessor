import java.util.List;

public class OrderProcessor {
    private List<Order> orders;

    public OrderProcessor(List<Order> orders) {
        this.orders = orders;
    }

    // Метод для обработки заказов
    public void processOrders() {
        for (Order order : orders) {
            if (order.getStatus().equals("confirmed")) {
                sendConfirmationEmail(order);
                updateInventory(order);
                generateInvoice(order);
            } else if (order.getStatus().equals("shipped")) {
                updateShippingStatus(order);
            }
        }
    }

    // Метод для отправки подтверждения по электронной почте
    private void sendConfirmationEmail(Order order) {
        System.out.println("Sending confirmation email for order #"
                + order.getId());
    }

    // Метод для обновления запасов на складе
    private void updateInventory(Order order) {
        System.out.println("Updating inventory for order #"
                + order.getId());
    }

    // Метод для генерации счета
    private void generateInvoice(Order order) {
        System.out.println("Generating invoice for order #"
                + order.getId());
    }

    // Метод для обновления статуса доставки
    private void updateShippingStatus(Order order) {
        System.out.println("Updating shipping status for order #"
                + order.getId());
    }
}

class Order {
    private int id;
    private String status;

    public Order(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}