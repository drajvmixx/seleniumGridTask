package builder;

import model.AddToCartRequest;
import model.Product;

public class AddToCartRequestBuilder {
    private String productCode;
    private int quantity;

    public AddToCartRequestBuilder setProductCode(String productCode) {
        this.productCode = productCode;
        return this;
    }

    public AddToCartRequestBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public AddToCartRequest build() {
        return new AddToCartRequest(new Product(productCode), quantity);
    }
}
