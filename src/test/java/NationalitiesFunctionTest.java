import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NationalitiesFunctionTest {

    private RequestSpecification reqSpec;
    private Cookies cookies;
    private String national_id;
    private HashMap<String ,String> reqBody;
    private HashMap<String,String> upReqBody;

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
    public void newNationalityTest(){

        reqBody = new HashMap<>();
        reqBody.put("name","QA331 Hasan99 Nation44");

        national_id = given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .statusCode(201)
                .body("name",equalTo(reqBody.get("name")))
                .log().body()
                .extract().jsonPath().getString("id");
    }

    @Test(dependsOnMethods = "newNationalityTest")
    public void newNationalityNegativeTest(){

        reqBody = new HashMap<>();
        reqBody.put("name","QA331 Hasan99 Nation44");

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(reqBody)
                .when()
                .post("/school-service/api/nationality")
                .then()
                .log().body()
                .statusCode(400);
    }

    @Test(dependsOnMethods = "newNationalityNegativeTest")
    public void getNationalityTest(){

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .log().body()
                .when()
                .get("/school-service/api/nationality/" + national_id)
                .then()
                .statusCode(200)
                .body("name", equalTo(reqBody.get("name")))
                .log().body();

    }

    @Test(dependsOnMethods = "getNationalityTest")
    public void editNationalityTest(){

        upReqBody = new HashMap<>();
        upReqBody.put("id", national_id);
        upReqBody.put("name","QA33 Hasan44 Nation");

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .body(upReqBody)
                .log().body()
                .when()
                .put("/school-service/api/nationality/")
                .then()
                .log().body()
                .body("name", equalTo(upReqBody.get("name")))
                .statusCode(200);
    }

    @Test(dependsOnMethods = "editNationalityTest")
    public void deleteNationalityTest(){

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .when()
                .delete("/school-service/api/nationality/" + national_id)
                .then()
                .statusCode(200);

    }

    @Test(dependsOnMethods = "deleteNationalityTest")
    public void GetNationalityNegativeTest(){

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .log().body()
                .when()
                .get("/school-service/api/nationality/" + national_id)
                .then()
                .statusCode(400)
                .log().body();

    }

    @Test(dependsOnMethods = "GetNationalityNegativeTest")
    public void DeleteNationalityNegativeTest(){

        given()
                .spec(reqSpec)
                .cookies(cookies)
                .when()
                .delete("/school-service/api/nationality/" + national_id)
                .then()
                .statusCode(400);

    }
}
