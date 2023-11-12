package christmas.model.dto;

import christmas.model.Day;
import christmas.model.order.Orders;
import java.util.List;

public record CustomerResponse(int day, List<OrderResponse> orderResponses) {

    public static CustomerResponse of(final Day day, final Orders orders) {
        int dayValue = day.getDay();
        List<OrderResponse> orderResponses = orders.orders()
                .stream()
                .map(order -> OrderResponse.of(order.getMenuName(), order.getSize()))
                .toList();

        return new CustomerResponse(dayValue, orderResponses);
    }
}
