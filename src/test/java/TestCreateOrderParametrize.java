import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrderParametrize {
    ForClassController forClassController = new ForClassController();
    String path;
    Integer statusCode;

    public TestCreateOrderParametrize(String path, Integer statusCode) {
        this.path = path;
        this.statusCode = statusCode;
    }

    @Parameterized.Parameters
    public static Object[][] get() {
        return new Object[][]{
                {"src/test/resources/CreateOrderWithColorBlack.json", 201},
                {"src/test/resources/CreateOrderWithColorGrey.json", 201},
                {"src/test/resources/CreateOrderWithColorBlackAndGrey.json", 201},
                {"src/test/resources/CreateOrderWithoutColor.json", 201}
        };
    }

    @Test
    public void testCreateOrderWithColorBlack() {
        File json = new File(path);
        forClassController
                .postOrder(json)
                .then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(statusCode);
    }
}
