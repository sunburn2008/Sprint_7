import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.File;


public class ForClassController {

    @Step("Создание заказа")
    public Response postOrder(File file) {
        return Specification.getRequestSpecification().body(file).post("/api/v1/orders");
    }
}
