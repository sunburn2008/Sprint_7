import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateCourierWithIncompleteParameters {

    CourierController courierController = new CourierController();

    String login = Constant.RANDOM_LOGIN;
    String password = null;
    String firstName = null;

    @Test
    @Description("Создание курьера c неполными параметрами")
    public void testCreateCourierWithIncompleteParameters() {
        courierController.postCourier(login, password, firstName)
                .then()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }
}
