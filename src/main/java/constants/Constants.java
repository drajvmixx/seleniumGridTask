package constants;

public interface Constants {
    int WAIT_TIMEOUT = 30;
    static final String WEB_URL = "https://www.kruidvat.nl";
    static final String ANONYMOUS_CART_PATH = "/api/v2/kvn/users/anonymous/carts";
    static final String ADD_ITEM_TO_CART_PATH = "/api/v2/kvn/users/anonymous/carts/{sessionId}/entries";
    static final String ADD_ITEM_TO_CART_BODY = "{\"product\": {\"code\": \"2876350\"},\"quantity\": 1}";
    public static final String PRODUCT_CODE = "2876350";
}
