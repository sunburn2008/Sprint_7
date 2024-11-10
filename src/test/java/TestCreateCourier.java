import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateCourier {

    private CourierController courierController = new CourierController();

    @Test
    @Description("Создание курьера")
    public void testCreateFirstCourier() {
        String login = Constant.RANDOM_LOGIN;
        String password = "321123";
        String firstName = "Mihael_Borisov";

        Integer id = courierController.getCourier(login, password)
                .then().extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удалён: " + login);
        }

        courierController.postCourier(login, password, firstName)
                .then()
                .body("ok", equalTo(true))
                .and()
                .statusCode(201)
                .extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удалён: " + login);
        }
    }
}