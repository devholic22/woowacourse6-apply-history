package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Orders;
import christmas.model.policy.ChristmasPolicy;
import christmas.model.policy.DiscountPolicy;
import christmas.model.policy.SpecialDayPolicy;
import christmas.model.policy.WeekDayPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

public class PromotionTest {

    @Test
    @DisplayName("정책을 통한 프로모션 이름 조회 테스트")
    void findPromotionNameByPolicy() {
        // given
        DiscountPolicy discountPolicy = ChristmasPolicy.getInstance();
        String expectedName = "크리스마스 디데이 할인";

        // when
        String findPolicyName = Promotion.findNameByPolicy(discountPolicy);

        // then
        assertThat(findPolicyName).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("할인 정책 가능 조회 테스트")
    void countAvailableDiscountPolicyTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Customer customer = Customer.of(requestDay, orders);

        List<DiscountPolicy> expectedDiscountPolicies = List.of(
                ChristmasPolicy.getInstance(),
                WeekDayPolicy.getInstance(),
                SpecialDayPolicy.getInstance()
        );

        // when
        List<DiscountPolicy> policies = Promotion.collectPoliciesByRequest(customer);

        // then
        assertThat(policies.size()).isEqualTo(3);
        assertThat(policies.containsAll(expectedDiscountPolicies)).isTrue();
    }
}
