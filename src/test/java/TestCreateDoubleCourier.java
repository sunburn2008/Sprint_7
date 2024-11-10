import io.qameta.allure.Description;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class TestCreateDoubleCourier {
    CourierController courierController = new CourierController();

    String login = Constant.RANDOM_LOGIN;
    String password = "321123";
    String firstName = "Mihael_Borisov";

    @Test
    @Description("Создание дубля курьера")
    public void testCheckDoubleCourier() {
        Integer id = courierController.getCourier(login, password)
                .then().extract().jsonPath().get("id");

        if (id != null) {
            courierController.deleteCourier(id).then().statusCode(200);
            System.out.println("\nКурьер удален");
        }

        courierController.postCourier(login, password, firstName).then().extract().jsonPath().get("id");
        courierController.postCourier(login, password, firstName)
                .then()
                .assertThat()
                .body("message", equalTo("Этот логин уже используется"))
                .and()
                .statusCode(409);

        courierController.deleteCourier(id).then().statusCode(200);
        System.out.println("\nКурьер удалён: " + login);
    }
}
