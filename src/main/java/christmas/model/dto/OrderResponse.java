package christmas.model.dto;

public record OrderResponse(String name, int size) {

    public static OrderResponse of(final String name, final int size) {
        return new OrderResponse(name, size);
    }
}
