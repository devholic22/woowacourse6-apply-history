import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NameTest {

    @Test
    @DisplayName("정상적인 이름 리스트 입력 시 모두 저장되어야 한다.")
    public void createNameListTest() {
        // given
        String nameInput = "test1, test2, test3";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("중복 이름이 있다면 한 번만 저장되어야 한다.")
    public void duplicateNameTest() {
        // given
        String nameInput = "test1, test1, test2";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(2);
    }
}
