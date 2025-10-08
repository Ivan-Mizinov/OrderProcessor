import java.util.List;

public class OrderProcessingFacade {
    private final List<Order> orders;
    private final EmailConfirmationService emailService;
    private final InventoryUpdateService inventoryService;
    private final InvoiceGenerationService invoiceService;
    private final ShippingStatusUpdateService shippingService;

    public OrderProcessingFacade(List<Order> orders) {
        this.orders = orders;
        this.emailService = new EmailConfirmationService();
        this.inventoryService = new InventoryUpdateService();
        this.invoiceService = new InvoiceGenerationService();
        this.shippingService = new ShippingStatusUpdateService();
    }

    // Метод для обработки заказов
    public void handleOrders() {
        for (Order order : orders) {
            if (order.getStatus().equals("confirmed")) {
                emailService.sendConfirmationEmail(order);
                inventoryService.updateInventory(order);
                invoiceService.generateInvoice(order);
            } else if (order.getStatus().equals("shipped")) {
                shippingService.updateShippingStatus(order);
            }
        }
    }
}

// Класс для отправки подтверждения по электронной почте
class EmailConfirmationService {
    public void sendConfirmationEmail(Order order) {
        System.out.println("Sending confirmation email for order #" + order.getId());
    }
}

// Класс для обновления запасов на складе
class InventoryUpdateService {
    public void updateInventory(Order order) {
        System.out.println("Updating inventory for order #" + order.getId());
    }
}

// Класс для генерации счета
class InvoiceGenerationService {
    public void generateInvoice(Order order) {
        System.out.println("Generating invoice for order #" + order.getId());
    }
}

// Класс для обновления статуса доставки
class ShippingStatusUpdateService {
    public void updateShippingStatus(Order order) {
        System.out.println("Updating shipping status for order #" + order.getId());
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