import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarsTest {

    @Test
    public void 이름이_잘못되었다면_자동차_자체가_생성이_되지_않아야_한다() {
        // given
        String nameInput = "hello, springboot, test";
        List<Name> names = Name.createNameList(nameInput);

        // when
        Cars cars = Cars.from(names);

        // then
        Assertions.assertThat(cars.getCarListCount()).isEqualTo(2);
    }

    @Test
    public void 처음에는_승자가_없어야_한다() {
        // given
        String nameInput = "hello, java";
        List<Name> names = Name.createNameList(nameInput);
        Cars cars = Cars.from(names);

        // when
        List<String> winners = cars.getWinnersName(10);

        // then
        Assertions.assertThat(winners.size()).isEqualTo(0);
    }
}
