package christmas.model.dto;

public record OrderResponse(String name, int size, int cost) {

    public static OrderResponse of(final String name, final int size, final int cost) {
        return new OrderResponse(name, size, cost);
    }
}
