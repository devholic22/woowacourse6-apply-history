import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NameTest {

    @Test
    public void 정상적인_이름_리스트_입력_시_모두_저장되어야_한다() {
        // given
        String nameInput = "test1, test2, test3";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(3);
    }

    @Test
    public void 중복_이름이_있다면_한_번만_저장되어야_한다() {
        // given
        String nameInput = "test1, test1, test2";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(2);
    }

    @Test
    public void 이름은_1자_이상_5자_이하여야만_저장된다() {
        // given
        String nameInput = "hello12345, test1";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(1);
    }

    @Test
    public void 빈_문자열_입력_시_빈_리스트를_반환해야_한다() {
        // given
        String nameInput = "";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList).isEmpty();
    }

    @Test
    public void 공백_이름이_입력된_경우_무시되어야_한다() {
        // given
        String nameInput = "test1, , test2, ";

        // when
        List<Name> nameList = Name.createNameList(nameInput);

        // then
        Assertions.assertThat(nameList.size()).isEqualTo(2);
    }

    @Test
    public void null_입력이_주어진_경우_예외가_발생해야_한다() {
        // given
        String nameInput = null;

        // when & then
        Assertions.assertThatThrownBy(() -> Name.createNameList(nameInput))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void Name_객체의_getName_메서드가_정상적으로_동작해야_한다() {
        // given
        Name name = Name.from("test");

        // when
        String retrievedName = name.getName();

        // then
        Assertions.assertThat(retrievedName).isEqualTo("test");
    }
}
