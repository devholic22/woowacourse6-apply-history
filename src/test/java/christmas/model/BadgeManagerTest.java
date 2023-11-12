package christmas.model;

import static christmas.model.Badge.NOT_THING;
import static christmas.model.Badge.SANTA;
import static christmas.model.Badge.TREE;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.model.manager.BadgeManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BadgeManagerTest {

    @Test
    @DisplayName("BadgeManager를 통한 Badge 조회 - 없음")
    void notFoundBadgeFindTest() {
        // given
        int cost = 3_000;

        // when
        Badge findBadge = BadgeManager.giveBadgeByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(NOT_THING);
    }

    @Test
    @DisplayName("BadgeManager를 통한 Badge 조회 - 트리")
    void treeBadgeFindTest() {
        // given
        int cost = 12_000;

        // when
        Badge findBadge = BadgeManager.giveBadgeByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(TREE);
    }

    @Test
    @DisplayName("BadgeManager를 통한 Badge 조회 - 산타")
    void santaBadgeFindTest() {
        // given
        int cost = 150_000;

        // when
        Badge findBadge = BadgeManager.giveBadgeByCost(cost);

        // then
        assertThat(findBadge).isEqualTo(SANTA);
    }
}
