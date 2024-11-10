import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestLoginCourier {
    CourierController courierController = new CourierController();
    String login = Constant.RANDOM_LOGIN;
    String password = "321123";
    String firstName = "Mihael_Borisov";

    @Test
    @Description("Логин существующего курьера")
    public void testLoginCourier() {

        courierController.postCourier(login, password, firstName);
        courierController.getCourier(login, password)
                .then()
                .body("id", notNullValue())
                .and()
                .statusCode(200)
                .extract().jsonPath().get("id");

        Integer id = courierController.getCourier(login, password)
                .then().extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удалён: " + login);
        }
    }
}
