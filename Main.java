//QUESTION 6- FOOD DELIVERY OBJECT MODELING SYSTEM 

import java.util.*;
class Address {
    String address;

    Address(String address) {
        this.address = address;
    }
}

class Customer {
    String name;
    Address address;

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }
}

class Restaurant {
    String name;

    Restaurant(String name) {
        this.name = name;
    }
}

class FoodItem {
    String name;
    double price;
    int quantity;

    FoodItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotal() {
        return price * quantity;
    }
}

class Order {
    ArrayList<FoodItem> items = new ArrayList<>();

    double deliveryCharge = 50;
    double taxRate = 0.05;

    void addItem(FoodItem item) {
        items.add(item);
    }

    double calculateSubtotal() {
        double subtotal = 0;

        for (FoodItem item : items) {
            subtotal += item.getTotal();
        }

        return subtotal;
    }

    double calculateDiscount() {
        double subtotal = calculateSubtotal();

        if (subtotal >= 1000) {
            return subtotal * 0.10;
        }

        return 0;
    }

    double calculateTax() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount();

        double amountAfterDiscount = subtotal - discount;

        return amountAfterDiscount * taxRate;
    }

    double calculateFinalBill() {
        double subtotal = calculateSubtotal();
        double discount = calculateDiscount();
        double tax = calculateTax();

        return subtotal - discount + tax + deliveryCharge;
    }

    void displayOrder(Customer customer, Restaurant restaurant) {
        System.out.println("Customer: " + customer.name);
        System.out.println("Restaurant: " + restaurant.name);
        System.out.println("Subtotal: " + calculateSubtotal());
        System.out.println("Discount: " + calculateDiscount());
        System.out.println("Tax: " + calculateTax());
        System.out.println("Delivery Charge: " + deliveryCharge);
        System.out.println("Final Bill: " + calculateFinalBill());
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String customerName = sc.nextLine();
        String addressText = sc.nextLine();
        String restaurantName = sc.nextLine();

        int n = sc.nextInt();

        Address address = new Address(addressText);
        Customer customer = new Customer(customerName, address);
        Restaurant restaurant = new Restaurant(restaurantName);

        Order order = new Order();

        for (int i = 0; i < n; i++) {
            String foodName = sc.next();
            double price = sc.nextDouble();
            int quantity = sc.nextInt();

            FoodItem item = new FoodItem(foodName, price, quantity);
            order.addItem(item);
        }

        order.displayOrder(customer, restaurant);

        sc.close();
    }
}