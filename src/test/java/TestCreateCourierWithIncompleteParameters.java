import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateCourierWithIncompleteParameters {

    CourierController courierController = new CourierController();

    @Test
    public void testCreateCourierWithIncompleteParameters() {
        String json = "{\"login\": \"Misha1308\"}";
        courierController.postCourier(json)
            .then()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }
}
