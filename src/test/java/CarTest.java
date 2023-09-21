import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CarTest {

    @Test
    public void 이름을_토대로_자동차가_생성되어야_한다() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        Assertions.assertThat(newCar.getName()).isEqualTo(name.getName());
    }

    @Test
    public void 이름이_잘못되었다면_자동차_자체가_생성이_되지_않아야_한다() {
        // given
        String nameInput = "hello, springboot, test";
        List<Name> names = Name.createNameList(nameInput);

        // when
        List<Car> cars = names.stream().map(Car::from).collect(Collectors.toList());

        // then
        Assertions.assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    public void 처음_진행된_거리는_0이어야_한다() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        Assertions.assertThat(newCar.isDistanceEqualTo(0)).isTrue();
    }
}
