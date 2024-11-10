import io.qameta.allure.Description;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;

public class TestGetOrderList {

    private final ForClassController forClassController = new ForClassController();
    private final CourierController courierController = new CourierController();
    private final OrderController orderController = new OrderController();

    String login = Constant.RANDOM_LOGIN;
    String password = "341143";
    String firstName = "Mihael_Borisov";

    @Test
    @Description("Получение списка заказов")
    public void testGetOrderList() {
        courierController.postCourier(login, password, firstName);

        Integer track = forClassController.postOrder(new File("src/test/resources/CreateOrderWithColorBlack.json"))
                .then().extract().jsonPath().get("track");

        Integer id = courierController.getCourier(login, password)
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