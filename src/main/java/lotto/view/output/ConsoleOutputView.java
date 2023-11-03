package lotto.view.output;

import lotto.model.dto.LottoResponse;
import java.util.List;

public class ConsoleOutputView implements OutputView {

    private static final String LOTTO_DELIMITER = ", ";

    @Override
    public void askInvestMoney() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printBoughtLottoSize(final int size) {
        System.out.println();
        System.out.println(size + "개를 구매했습니다.");
    }

    @Override
    public void printEachLottoNumbers(final List<LottoResponse> lottoResponses) {
        for (LottoResponse response : lottoResponses) {
            System.out.println("[" + String.join(LOTTO_DELIMITER, response.numbers()) + "]");
        }
    }
}
