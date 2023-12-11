import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsTest {

    @Test
    public void 이름이_잘못되었다면_자동차_자체가_생성이_되지_않아야_한다() {
        // given
        String nameInput = "hello, springboot, test";

        // when
        Cars cars = Cars.from(nameInput);

        // then
        assertThat(cars.getCarListCount()).isEqualTo(2);
    }

    @Test
    public void 처음에는_승자가_없어야_한다() {
        // given
        String nameInput = "hello, java";
        Cars cars = Cars.from(nameInput);

        // when
        List<String> winners = cars.getWinnersName(10);

        // then
        assertThat(winners.size()).isEqualTo(0);
    }
}
