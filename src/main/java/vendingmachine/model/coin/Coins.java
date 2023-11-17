package vendingmachine.model.coin;

import vendingmachine.model.Number;
import java.util.ArrayList;
import java.util.List;

public class Coins {

    private final List<Coin> coins;

    private Coins(final List<Coin> coins) {
        this.coins = List.copyOf(coins);
    }

    public static Coins from(final Number money) {
        List<Coin> coins = new ArrayList<>();
        while (money.canMinus(Coin.getMinimumValue())) {
            saveNewCoin(coins, money);
        }
        return new Coins(coins);
    }

    private static void saveNewCoin(final List<Coin> coins, final Number money) {
        Coin randomCoin = Coin.getRandomCoin();
        if (!money.canMinus(randomCoin.getAmount())) {
            return;
        }
        money.minus(randomCoin.getAmount());
        coins.add(randomCoin);
    }

    public int calculateSize(final Coin otherCoin) {
        return coins.stream()
                .filter(coin -> coin.isSame(otherCoin))
                .toList()
                .size();
    }

    public List<Coin> getCoins() {
        return List.copyOf(coins);
    }
}
