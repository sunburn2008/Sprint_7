import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateCourier {

    private CourierController courierController = new CourierController();

    @Test
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

        String json = String.format("{\"login\": \"%s\", \"password\": \"%s\", \"firstName\": \"%s\"}", login, password, firstName);

        courierController.postCourier(json)
                .then()
                .body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }
}