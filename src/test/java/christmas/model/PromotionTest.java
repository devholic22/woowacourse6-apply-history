package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Orders;
import christmas.model.policy.DiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

class PromotionTest {

    @Test
    @DisplayName("할인 정책 가능 조회")
    void countAvailableDiscountPolicyTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        int expectedPoliciesSize = 3;

        // when
        List<DiscountPolicy> policies = Promotion.collectPoliciesByRequest(requestDay, orders);

        // then
        assertThat(policies.size()).isEqualTo(expectedPoliciesSize);
    }
}
