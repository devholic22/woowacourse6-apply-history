import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DiceTest {

    @Test
    @DisplayName("숫자가 조건값보다 크거나 같으면 위치를 1 증가")
    public void carMovableDiceTest() {
        // given
        Name name = Name.from("hello");
        Car car = Car.from(name);
        Dice movableDice = new DiceMovableImpl();

        // when
        car.racing(movableDice);

        // then
        Assertions.assertThat(car.isDistanceEqualTo(1)).isTrue();
    }

    @Test
    @DisplayName("숫자가 조건값보다 작으면 위치는 그대로여야 한다.")
    public void randomNumberLowerThanConditionTest() {
        // given
        Name name = Name.from("hello");
        Car car = Car.from(name);
        Dice movableDice = new LowerDiceImpl();

        // when
        car.racing(movableDice);

        // then
        Assertions.assertThat(car.isDistanceEqualTo(0)).isTrue();
    }

    @Test
    @DisplayName("숫자가 조건값보다 커도 위치는 그대로여야 한다.")
    public void randomNumberHigherThanConditionTest() {
        // given
        Name name = Name.from("hello");
        Car car = Car.from(name);
        Dice movableDice = new HigherDiceImpl();

        // when
        car.racing(movableDice);

        // then
        Assertions.assertThat(car.isDistanceEqualTo(0)).isTrue();
    }
}
