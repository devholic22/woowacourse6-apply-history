package vendingmachine.model.product;

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
}
