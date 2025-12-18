package patterns.behaviour.mediator;

interface Component {
    public void setMediator(Mediator mediator);
    public String getName();
}

class OrderService implements Component {
    Mediator mediator;
    private final String name;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public OrderService(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void processOrder() {
        System.out.println("Order placed: ");
        this.mediator.processInventory();
    }
}

class InventoryService  implements Component {
    private final String name;
    Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public InventoryService(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void processInventory() {
        System.out.println("Inventory updated: ");
        this.mediator.processInvoice();
    }
}

class InvoiceService implements Component {
    private String name;
    Mediator mediator;

    public InvoiceService(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public void processInvoice() {
        System.out.println("Invoice created: ");
        mediator.completeProcessing();
    }
}

interface Mediator {
    public void processOrder();
    public void processInventory();
    public void processInvoice();
    public void completeProcessing();
    public void setComponent(Component component);
}


class CartService implements Mediator {

    private OrderService orderService;
    private InventoryService inventoryService;
    private InvoiceService invoiceService;

    public void setComponent(Component component) {
        component.setMediator(this);
        if(component.getName().equals("OrderService")) {
            this.orderService = (OrderService) component;
        } else if(component.getName().equals("InventoryService")) {
            this.inventoryService = (InventoryService) component;
        } else {
            this.invoiceService = (InvoiceService) component;
        }
    }

    @Override
    public void processOrder() {
        this.orderService.processOrder();
    }

    @Override
    public void processInventory() {
        this.inventoryService.processInventory();
    }

    @Override
    public void processInvoice() {
        this.invoiceService.processInvoice();
    }

    public void completeProcessing() {
        System.out.println("Order successfully placed");
    }
}

public class Solution {

    public static void main(String[] args) {
        Mediator mediator = new CartService();
        mediator.setComponent(new OrderService("OrderService"));
        mediator.setComponent(new InventoryService("InventoryService"));
        mediator.setComponent(new InvoiceService("InvoiceService"));
        mediator.processOrder();
    }
}
