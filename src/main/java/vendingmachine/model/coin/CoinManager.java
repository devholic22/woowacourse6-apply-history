package vendingmachine.model.coin;

import vendingmachine.model.dto.CoinResponse;
import java.util.Arrays;
import java.util.List;

public class CoinManager {

    private CoinManager() {

    }

    public static List<CoinResponse> convertCoinResponses(final Coins coins) {
        return Arrays.stream(Coin.values())
                .map(coin -> CoinResponse.of(coin.getAmount(), coins.calculateSize(coin)))
                .toList();
    }
}
