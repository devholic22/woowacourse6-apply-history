package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.order.Orders;
import christmas.model.policy.ChristmasPolicy;
import christmas.model.policy.DiscountPolicy;
import christmas.model.policy.SpecialDayPolicy;
import christmas.model.policy.WeekDayPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.List;

public class PromotionTest {

    private static final String PROMOTIONS =
    """
    크리스마스 디데이 할인, CHRISTMAS
    평일 할인, WEEK_DAY
    주말 할인, WEEK_END
    특별 할인, SPECIAL
    """;

    @ParameterizedTest(name = "{0} 정책에 따른 프로모션 이름 조회 테스트")
    @CsvSource(textBlock = PROMOTIONS)
    @DisplayName("정책을 통한 프로모션 이름 조회 테스트")
    void findPromotionNameByPolicy(final String name, final Promotion promotion) {
        // given
        DiscountPolicy discountPolicy = promotion.getPolicy();

        // when
        String findPolicyName = Promotion.findNameByPolicy(discountPolicy);

        // then
        assertThat(findPolicyName).isEqualTo(name);
    }

    @Test
    @DisplayName("할인 정책 가능 조회 테스트")
    void countAvailableDiscountPolicyTest() {
        // given
        Day requestDay = Day.from("3");
        Orders orders = Orders.from("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");

        List<DiscountPolicy> expectedDiscountPolicies = List.of(
                ChristmasPolicy.getInstance(),
                WeekDayPolicy.getInstance(),
                SpecialDayPolicy.getInstance()
        );

        // when
        List<DiscountPolicy> policies = Promotion.collectPoliciesByRequest(requestDay, orders);

        // then
        assertThat(policies.size()).isEqualTo(3);
        assertThat(policies.containsAll(expectedDiscountPolicies)).isTrue();
    }
}
