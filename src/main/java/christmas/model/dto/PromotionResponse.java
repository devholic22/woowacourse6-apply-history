package christmas.model.dto;

public record PromotionResponse(String name, int cost) {

    public static PromotionResponse of(final String name, final int cost) {
        return new PromotionResponse(name, cost);
    }
}
