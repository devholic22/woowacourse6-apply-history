package christmas.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @ParameterizedTest(name = "{0}원이 들어올 때 {1} Badge인가?")
    @CsvSource({
            "3000, NOT_THING",
            "6000, STAR",
            "12000, TREE",
            "25000, SANTA"
    })
    @DisplayName("각 입력값에 따른 적절한 Badge 조회")
    void findEachBadgeByCostTest(final int cost, final Badge badge) {
        // when
        Badge findBadge = Badge.findByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(badge);
    }
}
