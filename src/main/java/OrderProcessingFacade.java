import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderProcessingFacade {
    private final Map<String, OrderHandler> handlerMap;
    private final List<Order> orders;

    public OrderProcessingFacade(List<Order> orders) {
        this.orders = orders;
        this.handlerMap = new HashMap<>();
        // Инициализация мапы обработчиков
        handlerMap.put("confirmed", new ConfirmedOrderHandler(
                new EmailConfirmationService(),
                new InventoryUpdateService(),
                new InvoiceGenerationService()
        ));
        handlerMap.put("shipped", new ShippedOrderHandler(
                new ShippingStatusUpdateService()
        ));
    }

    /** Метод для обработки заказов.
     * Обрабатывает все заказы из списка, используя соответствующие обработчики,
     * для каждого заказа выбирается обработчик на основе его статуса.
     */
    public void handleOrders() {
        for (Order order : orders) {
            OrderHandler handler = handlerMap.get(order.getStatus());
            if (handler != null) {
                handler.handle(order);
            }
        }
    }
}

interface OrderHandler {
    /**
     * Обрабатывает заказ.
     *
     * @param order обрабатываемый заказ
     */
    void handle(Order order);
}

class ConfirmedOrderHandler implements OrderHandler {
    private final EmailConfirmationService emailService;
    private final InventoryUpdateService inventoryService;
    private final InvoiceGenerationService invoiceService;

    public ConfirmedOrderHandler(EmailConfirmationService emailService,
                                 InventoryUpdateService inventoryService,
                                 InvoiceGenerationService invoiceService) {
        this.emailService = emailService;
        this.inventoryService = inventoryService;
        this.invoiceService = invoiceService;
    }

    /**
     * Обрабатывает подтверждённый заказ: отправляет email-подтверждение,
     * обновляет запасы на складе и генерирует счёт.
     *
     * @param order обрабатываемый заказ
     */
    public void handle(Order order) {
        emailService.sendConfirmationEmail(order);
        inventoryService.updateInventory(order);
        invoiceService.generateInvoice(order);
    }
}

class ShippedOrderHandler implements OrderHandler {
    private final ShippingStatusUpdateService shippingService;

    public ShippedOrderHandler(ShippingStatusUpdateService shippingService) {
        this.shippingService = shippingService;
    }

    public void handle(Order order) {
        shippingService.updateShippingStatus(order);
    }
}

// Класс для отправки подтверждения по электронной почте
class EmailConfirmationService {
    /**
     * Отправляет email-подтверждение для указанного заказа.
     *
     * @param order заказ, для которого отправляется подтверждение
     */
    public void sendConfirmationEmail(Order order) {
        System.out.println("Sending confirmation email for order #" + order.getId());
    }
}

// Класс для обновления запасов на складе
class InventoryUpdateService {
    /**
     * Обновляет запасы на складе в соответствии с указанным заказом.
     *
     * @param order заказ, на основе которого обновляются запасы
     */
    public void updateInventory(Order order) {
        System.out.println("Updating inventory for order #" + order.getId());
    }
}

// Класс для генерации счета
class InvoiceGenerationService {
    /**
     * Генерирует счёт для указанного заказа.
     *
     * @param order заказ, на основе которого обновляются запасы
     */
    public void generateInvoice(Order order) {
        System.out.println("Generating invoice for order #" + order.getId());
    }
}

// Класс для обновления статуса доставки
class ShippingStatusUpdateService {
    /**
     * Генерирует счёт для указанного заказа.
     *
     * @param order заказ, на основе которого обновляются запасы
     */
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