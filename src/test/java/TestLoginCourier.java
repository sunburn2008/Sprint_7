import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestLoginCourier {
    CourierController courierController = new CourierController();
    String login = "Pisha" + (int) (Math.random() * 100);

    @Test
    @Description("Логин существующего курьера")
    public void testLoginCourier() {
        String json = "{\"login\": \"" + login + "\", \"password\": \"321123\", \"firstName\": \"Mihael_Borisov\"}";

        courierController.postCourier(json);
        courierController.getCourier(login, "321123")
                .then()
                .body("id", notNullValue())
                .and()
                .statusCode(200)
                .extract().jsonPath().get("id");

        Integer id = courierController.getCourier(login, "321123")
                .then().extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удалён: " + login);
        }
    }
}
