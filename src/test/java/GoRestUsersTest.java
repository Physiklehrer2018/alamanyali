
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestUsersTest {

    private RequestSpecification reqSpec;
    private HashMap<String,String> requestBody;
    private Object userId;

    @BeforeClass
    public void setup(){

        RestAssured.baseURI = "https://gorest.co.in/";

        reqSpec = given()
                .log().uri()
                .header("Authorization","Bearer 976e5357003cefdc2c1d66557ad1d5309e785bf4df3d1ccb2eb08a5406e9e673")
                .contentType(ContentType.JSON);

        requestBody = new HashMap<>();
        requestBody.put("name","Herr Fizik");
        requestBody.put("email","fizikci@dieburg.com");
        requestBody.put("gender","male");
        requestBody.put("status","inactive");

        System.out.println(requestBody);
    }

    @Test(priority = 1)
    public void newUserTest(){

        userId = given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");
    }

    @Test(priority = 2)
    public void editUserTest(){

        HashMap<String, String> updateRequestBody = new HashMap<>();
        updateRequestBody.put("name", "Updated Blah Blah Name");

        given()
                .spec(reqSpec)
                .body(updateRequestBody)
                .when()
                .put("/public/v2/users/" + userId)
                .then()
                .body("name",equalTo(updateRequestBody.get("name")))
                .statusCode(200);
    }

    @Test(priority = 3)
    public void deleteUserTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + userId)
                .then()
                .log().body()
                .statusCode(204);
    }
}
