import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZippoTest {

    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "http://api.zippopotam.us";
    }

    @Test
    public void checkingStatusCode(){

        given()
                .when()
                .get("/tr/45420")
                .then()
                .statusCode(200);
    }

    @Test
    public void loggingRequestDetails(){
        given()
                .log().all()
                .when()
                .get("/tr/45420")
                .then()
                .statusCode(200);

    }

    @Test
    public void loggingResponseDetails(){
        given()
                .log().all()
                .when()
                .get("/de/64283")
                .then()
                .log().body()
                .statusCode(200);

    }

    @Test
    public void checkContentType(){

        given()
                .when()
                .get("/tr/45420")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test
    public void validateCountryType(){

        given()
                .when()
                .get("/de/64807")
                .then()
                .body("country", equalTo("Germany"))
                .statusCode(200);
    }

    @Test
    public void validateCountryAbv(){

        given()
                .when()
                .get("/tr/45420")
                .then()
                .body("'country abbreviation'", equalTo("TR"))
                .statusCode(200);
    }

    @Test
    public void validateZipcode(){

        given()
                .when()
                .get("/tr/45420")
                .then()
                .body("'post code'", equalTo("45420"))
                .statusCode(200);
    }

    @Test
    public void validateStateTest(){

        given()
                .when()
                .get("/tr/45420")
                .then()
                .body("places[0].state", equalTo("Manisa"))
                .statusCode(200);

    }

    @Test
    public void pathParametersTest(){

        String country = "us";
        String zipcode = "45420";

        given()
                .pathParam("country", country)
                .pathParam("zipcode", zipcode)
                .when()
                .get("/{country}/{zipcode}")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void queryParameters(){

        String gender = "male";
        String status = "active";

        given()
                .param("gender", gender)
                .param("status", status)
                .when()
                .get("https://gorest.co.in/public/v2/users")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void extractValueTest(){

        Object countryInfo = given()

                .when()
                .get("/de/64807")
                .then()
                .extract().path("country");

        System.out.println(countryInfo);
    }
}
