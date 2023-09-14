import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    @DisplayName("이름은 1자 이상 5자 이하여야만 저장된다.")
    public void nameLengthTest() {
        // given
        String nameInput = "hello12345, test1";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("빈 문자열 입력 시 빈 리스트를 반환해야 한다.")
    public void emptyInputTest() {
        // given
        String nameInput = "";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList).isEmpty();
    }

    @Test
    @DisplayName("공백 이름이 입력된 경우 무시되어야 한다.")
    public void whiteSpaceInputTest() {
        // given
        String nameInput = "test1, , test2, ";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("null 입력이 주어진 경우 예외가 발생해야 한다.")
    public void invalidInputTest() {
        // given
        String nameInput = null;

        // when & then
        assertThrows(NullPointerException.class, () -> {
            Name.createNameList(nameInput);
        });
    }
}
