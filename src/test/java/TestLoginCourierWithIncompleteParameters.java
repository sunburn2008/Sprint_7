import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestLoginCourierWithIncompleteParameters {

    CourierController courierController = new CourierController();

    @Test
    @Description("Логин курьера с неполными параметрами")
    public void testLoginCourierWithIncompleteParameters() {
        courierController.getCourier(null, null)
                .then()
                .log().all()
                .body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }
}
