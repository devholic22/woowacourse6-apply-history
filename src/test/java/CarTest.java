import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class CarTest {

    @Test
    @DisplayName("이름을 토대로 자동차가 생성되어야 한다.")
    public void carNameTest() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        Assertions.assertThat(newCar.getName()).isEqualTo(name.getName());
    }

    @Test
    @DisplayName("이름이 잘못되었다면 자동차 자체가 생성이 되지 않아야 한다.")
    public void carNameErrorTest() {
        // given
        String nameInput = "hello, springboot, test";
        List<Name> names = Name.createNameList(nameInput);

        // when
        List<Car> cars = names.stream().map(Car::from).collect(Collectors.toList());

        // then
        Assertions.assertThat(cars.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("처음 진행된 거리는 0이어야 한다.")
    public void initCarDistanceTest() {
        // given
        Name name = Name.from("hello");

        // when
        Car newCar = Car.from(name);

        // then
        Assertions.assertThat(newCar.isDistanceEqualTo(0)).isTrue();
    }
}
