package christmas.model;

import christmas.model.order.Orders;

public class Customer {

    private final Day day;
    private final Orders orders;

    private Customer(final Day day, final Orders orders) {
        this.day = day;
        this.orders = orders;
    }

    public static Customer of(final Day day, final Orders orders) {
        return new Customer(day, orders);
    }

    public int calculateTotalCost() {
        return orders.calculateTotalCost();
    }

    public Day getDay() {
        return day;
    }

    public Orders getOrders() {
        return orders;
    }
}
