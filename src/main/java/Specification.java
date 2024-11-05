import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class Specification {
    private static final String baseURI = "https://qa-scooter.praktikum-services.ru";

    public static RequestSpecification getRequestSpecification() {
        return given()
            .header("Content-Type", "application/json")
            .baseUri(baseURI)
            .when();
    }

}
