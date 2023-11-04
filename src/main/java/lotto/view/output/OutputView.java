package lotto.view.output;

import lotto.model.dto.LottoResponse;
import java.util.List;

public interface OutputView {

    void askInvestMoney();
    void printBoughtLottoSize(final int size);
    void printEachLottoNumbers(final List<LottoResponse> lottoResponses);
    void askGoalNumbers();
    void askBonusNumber();
    void alertResult();
    void printEachPrize(final String condition, final int prize, final int count);
    void printProfitRate(final double rate);
}
