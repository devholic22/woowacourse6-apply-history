package vendingmachine.model.product;

import vendingmachine.model.Cost;
import java.util.Arrays;
import java.util.List;

public class Products {

    private static final String PRODUCT_SPLITTER = ";";

    private final List<Product> products;

    private Products(final List<Product> products) {
        this.products = products;
    }

    public static Products from(final String productsInput) {
        List<Product> products = Arrays.stream(productsInput.split(PRODUCT_SPLITTER))
                .map(Product::from)
                .toList();

        return new Products(products);
    }

    public boolean isMoneyCanBuy(final int money) {
        int minimumCost = getMinimumCost();
        return money >= minimumCost;
    }

    private int getMinimumCost() {
        int minimumCost = Integer.MAX_VALUE;
        List<Cost> costs = products.stream()
                .map(Product::getCost)
                .toList();

        for (Cost cost : costs) {
            if (cost.isMoneyCanBuy(minimumCost)) {
                minimumCost = Math.min(cost.getCost(), minimumCost);
            }
        }

        return minimumCost;
    }

    public Product findProductByName(final String name) {
        return products.stream()
                .filter(product -> product.isYourName(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
