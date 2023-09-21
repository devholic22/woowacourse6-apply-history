import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class DiceTest {

    @Test
    public void 숫자가_조건값보다_크거나_같으면_위치를_1_증가() {
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
    public void 숫자가_조건값보다_작으면_위치는_그대로여야_한다() {
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
    public void 숫자가_조건값보다_커도_위치는_그대로여야_한다() {
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
