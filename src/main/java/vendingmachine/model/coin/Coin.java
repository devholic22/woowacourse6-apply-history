package vendingmachine.model.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    // 추가 기능 구현

    public static Coin getRandomCoin() {
        List<Integer> coinValues = Arrays.stream(values())
                .map(coin -> coin.amount)
                .toList();
        int coinValue = Randoms.pickNumberInList(coinValues);

        return Coin.findByValue(coinValue);
    }

    private static Coin findByValue(final int value) {
        return Arrays.stream(values())
                .filter(coin -> coin.amount == value)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static int getMinimumValue() {
        return COIN_10.amount;
    }

    public boolean isSame(final Coin otherCoin) {
        return amount == otherCoin.amount;
    }

    public int getAmount() {
        return amount;
    }
}
