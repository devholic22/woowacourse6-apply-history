package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BonusManagerTest {

    @Test
    @DisplayName("증정 이벤트 기능 테스트 - 없음")
    void emptyBonusTest() {
        // given
        int cost = 100_000;

        // when
        List<Order> bonusOrders = BonusManager.giveBonusOrdersForCost(cost);

        // then
        assertThat(bonusOrders).isEmpty();
    }

    @Test
    @DisplayName("증정 이벤트 기능 테스트 - 지급 완료")
    void receiveBonusTest() {
        // given
        int cost = 150_000;

        // when
        List<Order> bonusOrders = BonusManager.giveBonusOrdersForCost(cost);

        // then
        assertThat(bonusOrders).isNotEmpty();
    }
}
