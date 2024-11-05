import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestLoginCourier {
    CourierController courierController = new CourierController();

    String name = "Pisha" + (int)(Math.random() * 100);

        @Test
        public void testLoginCourier() {
            String json = "{\"login\": \"" + name + "\", \"password\": \"321123\", \"firstName\": \"Mihael_Borisov\"}";
            courierController.postCourier(json);
            courierController.getCourier(name, "321123")
                .then()
                .body("id", notNullValue())
                .and()
                .statusCode(200);
        }
}
