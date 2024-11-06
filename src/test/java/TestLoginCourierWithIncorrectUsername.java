import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestLoginCourierWithIncorrectUsername {

    CourierController courierController = new CourierController();

    @Test
    @Description("Логин курьера с некорректными параметрами")
    public void testLoginCourierWithIncorrectUsername() {
        courierController.getCourier("Misha2288", "Misha2288")
                .then()
                .body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
}