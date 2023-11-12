package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.manager.GiftManager;
import christmas.model.order.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GiftManagerTest {

    @Test
    @DisplayName("증정 이벤트 기능 테스트 - 없음")
    void emptyBonusTest() {
        // given
        int cost = 100_000;

        // when
        Orders giftOrders = GiftManager.giveGiftsForCost(cost);

        // then
        assertThat(giftOrders.getOrders()).isEmpty();
    }

    @Test
    @DisplayName("증정 이벤트 기능 테스트 - 지급 완료")
    void receiveBonusTest() {
        // given
        int cost = 150_000;

        // when
        Orders giftOrders = GiftManager.giveGiftsForCost(cost);

        // then
        assertThat(giftOrders.getOrders()).isNotEmpty();
    }
}
