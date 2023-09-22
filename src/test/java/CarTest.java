import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {

    @Test
    public void 이름을_토대로_자동차가_생성되어야_한다() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        assertThat(newCar.getName()).isEqualTo(name.getName());
    }

    @Test
    public void 처음_진행된_거리는_0이어야_한다() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        assertThat(newCar.isDistanceEqualTo(0)).isTrue();
    }
}
