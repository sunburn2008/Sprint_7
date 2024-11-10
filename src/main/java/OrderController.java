import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;

public class OrderController {

    @Step("Назначение заказа курьеру")
    public Response acceptOrder(Integer track, Integer courierId) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("courierId", courierId);
        params.put("track", track);
        return Specification.getRequestSpecification().put(String.format("/api/v1/orders/accept/%d?courierId=%d", track, courierId));
    }

    @Step("Назначение заказа курьеру")
    public Response getBodyOrder(Integer courierId) {
        HashMap<String, Integer> params = new HashMap<>();
        params.put("courierId", courierId);
        return Specification.getRequestSpecification().get(String.format("/api/v1/orders?courierId=%d", courierId));
    }
}
