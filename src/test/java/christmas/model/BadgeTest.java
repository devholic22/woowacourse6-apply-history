package christmas.model;

import static christmas.model.Badge.NOT_THING;
import static christmas.model.Badge.SANTA;
import static christmas.model.Badge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BadgeTest {

    @Test
    @DisplayName("Badge 조회 - 없음")
    void notFoundBadgeFindTest() {
        // given
        int cost = 3_000;

        // when
        Badge findBadge = Badge.findByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(NOT_THING);
    }

    @Test
    @DisplayName("Badge 조회 - 트리")
    void treeBadgeFindTest() {
        // given
        int cost = 12_000;

        // when
        Badge findBadge = Badge.findByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(TREE);
    }

    @Test
    @DisplayName("Badge 조회 - 산타")
    void santaBadgeFindTest() {
        // given
        int cost = 150_000;

        // when
        Badge findBadge = Badge.findByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(SANTA);
    }
}
