import io.qameta.allure.Description;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestGetOrderList {

    private final ForClassController forClassController = new ForClassController();
    private final CourierController courierController = new CourierController();
    private final OrderController orderController = new OrderController();

    private String json = "{\"login\": \"" + Constant.RANDOM_LOGIN + "\", \"password\": \"341143\", \"firstName\": \"Mihael_Borisov\"}";

    @Test
    @Description("Получение списка заказов")
    public void testGetOrderList() {
        Integer track = forClassController.postOrder(new File("src/test/resources/CreateOrderWithColorBlack.json"))
                .then().extract().jsonPath().get("track");

        courierController.postCourier(json);
        String loginCourier = Constant.RANDOM_LOGIN;
        String passwordCourier = "341143";

        Integer id = courierController.getCourier(loginCourier, passwordCourier)
                .then()
                .extract().jsonPath().get("id");

        orderController.acceptOrder(track, id);

        orderController.getBodyOrder(id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}