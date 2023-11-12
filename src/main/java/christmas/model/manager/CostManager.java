package christmas.model.manager;

import christmas.model.dto.OrderResponse;
import christmas.model.dto.PromotionResponse;
import java.util.List;

public class CostManager {

    public static int calculatePromotionCost(final List<PromotionResponse> promotions) {
        return promotions.stream()
                .mapToInt(PromotionResponse::cost)
                .sum();
    }

    public static int calculateGiftOrdersCost(final List<OrderResponse> giftOrders) {
        return giftOrders.stream()
                .mapToInt(OrderResponse::cost)
                .sum();
    }
}
