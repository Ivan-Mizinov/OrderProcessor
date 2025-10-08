import java.util.List;

public class OrderProcessingFacade {
    private List<Order> orders;
    private OrderEmailSender emailSender;

    public OrderProcessingFacade(List<Order> orders) {
        this.orders = orders;
        this.emailSender = new OrderEmailSender();
    }

    // Метод для обработки заказов
    public void handleOrders() {
        for (Order order : orders) {
            if (order.getStatus().equals("confirmed")) {
                emailSender.sendConfirmationEmail(order);
                updateInventory(order);
                generateInvoice(order);
            } else if (order.getStatus().equals("shipped")) {
                updateShippingStatus(order);
            }
        }
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

// Класс для отправки подтверждения по электронной почте
class OrderEmailSender {
    public void sendConfirmationEmail(Order order) {
        System.out.println("Sending confirmation email for order #" + order.getId());
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