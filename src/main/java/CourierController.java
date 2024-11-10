import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.HashMap;


public class CourierController {

    public Response postCourier(String login, String password, String firstName) {
        HashMap<String,String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", password);
        map.put("firstName", firstName);
        return Specification.getRequestSpecification().body(map).post("/api/v1/courier");
    }

    public Response deleteCourier(Integer id) {
        return Specification.getRequestSpecification().delete(String.format("/api/v1/courier/%d", id));
    }

    @Step("Получение id курьера")
    public Response getCourier(String login, String pass) {
        HashMap<String, String> map = new HashMap<>();
        map.put("login", login);
        map.put("password", pass);
        return Specification.getRequestSpecification().body(map).post("/api/v1/courier/login");
    }

}
