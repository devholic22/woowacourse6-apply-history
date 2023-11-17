package vendingmachine.model.dto;

public record CoinResponse(int value, int size) {

    public static CoinResponse of(final int value, final int size) {
        return new CoinResponse(value, size);
    }
}
