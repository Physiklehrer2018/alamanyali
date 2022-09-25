
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CountriesFunctionTest {

    private RequestSpecification reqSpec;
    private Cookies cookies;
    private String country_id;

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://demo.mersys.io";

        reqSpec = given()
                .log().body()
                .contentType(ContentType.JSON);
    }

    @Test
    public void loginTest(){

        HashMap<String,String> credentials = new HashMap<>();
        credentials.put("username","richfield.edu");
        credentials.put("password","Richfield2020!");
        credentials.put("rememberMe","true");

        cookies = given()
                .spec(reqSpec)
                .body(credentials)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200)
                .extract().detailedCookies();
    }

    @Test(dependsOnMethods = "loginTest")
    public void newCountry(){

        HashMap<String,String> reqBody = new HashMap<>();
        reqBody.put("name","NewQA ZelihaCountry");
        reqBody.put("code","NQASZC");
        reqBody.put("hasState","true");

        country_id = given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/countries")
                .then()
                .log().body()
                .statusCode(201)
                .body("name", equalTo(reqBody.get("name")))
                .body("code",equalTo(reqBody.get("code")))
                .extract().jsonPath().getString("id");

    }

    @Test(dependsOnMethods = "newCountry")
    public void editCountry(){

        HashMap<String,String> updatedReqBody = new HashMap<>();
        updatedReqBody.put("id", country_id);
        updatedReqBody.put("name","NewQA  HuseyinCountry");
        updatedReqBody.put("code","NQAHC");
        updatedReqBody.put("haState","false");

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(updatedReqBody)
                .when()
                .put("/school-service/api/countries")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(updatedReqBody.get("name")));
    }

    @Test(dependsOnMethods = "editCountry")
    public void deleteCountry(){

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .when()
                .delete("/school-service/api/countries/" + country_id)
                .then()
                .log().body()
                .statusCode(200);
    }
}
