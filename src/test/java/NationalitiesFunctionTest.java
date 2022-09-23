import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class NationalitiesFunctionTest {

    private RequestSpecification reqSpec;
    private Cookies cookies;
    private String national_id;

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

    @Test
    public void newNationalityTest(){
        HashMap<String ,String> reqBody = new HashMap<>();
        reqBody.put("name","NewQA Mahmut Nation");

        national_id = given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .statusCode(201)
                .body("name",equalTo(reqBody.get("name")))
                .extract().jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "newNationalityTest")
    public void newNationalityNegativeTest(){

        HashMap<String ,String> reqBody = new HashMap<>();
        reqBody.put("name","NewQA Mahmut Nation");

        national_id = given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(400)
                .extract().jsonPath().getString("id");;

    }

    @Test
    public void getNationalityTest(){

    }

    @Test
    public void editNationalityTest(){

    }

    @Test
    public void deleteNationalityTest(){

    }

    @Test
    public void GetNationalityNegativeTest(){

    }

    @Test
    public void DeleteNationalityNegativeTest(){

    }
}
