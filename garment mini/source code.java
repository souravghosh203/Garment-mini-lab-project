
package javaapplication7;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class Garment {
    public String id;
    public String name;
    public String description;
    public String size;
    public String color;
    public double price;
    public int stockQuantity;


    Garment(String id, String name, String description, String size, String color, double price,
            int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    void updateStock(int quantity) {
        stockQuantity = stockQuantity + quantity;
    }

    double calculateDiscountPrice(double discountPercentage) {
        double discount = price * (discountPercentage / 100);
        return price - discount;
    }
}
class Fabric {
    public String id;
    public String type;
    public String color;
    public double pricePerMeter;

    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }

    public double calculateCost(double meters) {
        return pricePerMeter * meters;
    }
}
class Supplier {
    public String id;
    public String name;
    public String contactInfo;
    private List<Fabric> suppliedFabrics;

    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedFabrics = new ArrayList<>();
    }

    public void addFabric(Fabric fabric) {
        suppliedFabrics.add(fabric);
    }

    public List<Fabric> getSuppliedFabrics() {
        return suppliedFabrics;
    }
}
class Order {
    public String orderId;
    public Date orderDate;
    public double totalAmount;
    public List<Garment> garments;

    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalAmount = 0.0;
        this.garments = new ArrayList<>();
    }

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public double calculateTotalAmount() {
        totalAmount = 0;
        for (Garment garment : garments) {
            totalAmount += garment.price;
        }
        return totalAmount;
    }

    public void printOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Garments:");
        for (Garment garment : garments) {
            System.out.println("- " + garment.name + ": $" + garment.price);
        }
    }
}
class Customer {
    public String customerId;
    public String name;
    public String email;
    public String phone;
    private List<Order> orders;

    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.orders = new ArrayList<>();
    }

    public void placeOrder(Order order) {
        orders.add(order);
        System.out.println("Order placed successfully!");
        System.out.println("Order ID: " + order.orderId);
    }

    public void viewOrders() {
        System.out.println("Orders placed by customer " + name + ":");
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                order.printOrderDetails();
                System.out.println();
            }
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
class Inventory {
    public List<Garment> garments;

    public Inventory() {
        this.garments = new ArrayList<>();
    }

    public void addGarment(Garment garment) {
        garments.add(garment);
    }

    public void removeGarment(String id) {
        Garment garmentToRemove = findGarment(id);
        if (garmentToRemove != null) {
            garments.remove(garmentToRemove);
            System.out.println("Garment removed successfully!");
        } else {
            System.out.println("Garment not found.");
        }
    }

    public Garment findGarment(String id) {
        for (Garment garment : garments) {
            if (garment.id.equals(id)) {
                return garment;
            }
        }
        return null;
    }
}
public class JavaApplication7 {
    public static void main(String[] args) {

        Garment garment1 = new Garment("G001", "Shirt", "Cotton shirt", "M", "Blue", 35.99, 10);
        Garment garment2 = new Garment("G002", "Pants", "jeans", "L", "Black", 55.99, 5);
        Garment garment3 = new Garment("G003", "Panjabi", "Cotton", "L", "White", 45.99, 6);

        Inventory inventory = new Inventory();
        inventory.addGarment(garment1);
        inventory.addGarment(garment2);
        inventory.addGarment(garment3);

        Order order1 = new Order("O001", new Date());
        order1.addGarment(garment1);
        order1.addGarment(garment2);
        order1.addGarment(garment3);
        order1.calculateTotalAmount();

        Customer customer = new Customer("C001", "John Doe", "john@example.com", "123-456-7890");
        customer.placeOrder(order1);

        customer.viewOrders();
    }
}