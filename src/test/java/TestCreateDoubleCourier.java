import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateDoubleCourier {
    CourierController courierController = new CourierController();

    @Test
    @Description("Создание дубля курьера")
    public void testCheckDoubleCourier() {
        Integer id = courierController.getCourier("Pisha1308", "321123")
                .then().extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удален");
        }

        String json = "{\"login\": \"Pisha1308\", \"password\": \"321123\", \"firstName\": \"Mihael_Borisov\"}";
        courierController.postCourier(json);
        courierController.postCourier(json)
                .then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"))
                .and()
                .statusCode(409);
    }
}
