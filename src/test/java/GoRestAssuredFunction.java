import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GoRestAssuredFunction {

    private RequestSpecification reqSpec;
    private HashMap<String,String> requestBody;
    private Object user_id;


    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://gorest.co.in";

        reqSpec = given()
                .log().body()
                .header("Authorization","Bearer 976e5357003cefdc2c1d66557ad1d5309e785bf4df3d1ccb2eb08a5406e9e673")
                .contentType(ContentType.JSON);

        requestBody = new HashMap<>();
        requestBody.put("name","Physik Lehrer");
        requestBody.put("email","fraulehrerin11@frau.de");
        requestBody.put("gender","male");
        requestBody.put("status","active");
    }

    @Test
    public void newUserTest(){
        user_id = given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name",equalTo(requestBody.get("name")))
                .extract().path("id");

    }

    @Test(dependsOnMethods = "newUserTest")
    public void newUserNegativeTest(){

        given()
                .spec(reqSpec)
                .body(requestBody)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(422);
    }


    @Test(dependsOnMethods = "newUserNegativeTest")
    public void getUserAndValidateTest(){

        given()
                .spec(reqSpec)
                .when()
                .get("/public/v2/users/" + user_id)
                .then()
                .statusCode(200)
                .body("name", equalTo(requestBody.get("name")))
                .body("email", equalTo(requestBody.get("email")))
                .body("gender", equalTo(requestBody.get("gender")))
                .body("status", equalTo(requestBody.get("status")))
                .log().body();
    }


    @Test(dependsOnMethods = "getUserAndValidateTest")
    public void editUserTest(){

        HashMap<String,String> reqBodyUpdate = new HashMap<>();
        reqBodyUpdate.put("name","alamanyali22");

        given()
                .spec(reqSpec)
                .body(reqBodyUpdate)
                .when()
                .put("/public/v2/users/" + user_id)
                .then()
                .log().body()
                .body("name",equalTo(reqBodyUpdate.get("name")))
                .statusCode(200);
    }

    @Test(dependsOnMethods = "editUserTest")
    public void deleteUserTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user_id)
                .then()
                .log().body()
                .statusCode(204);

    }

    @Test(dependsOnMethods = "deleteUserTest")
    public void deleteUserNegativeTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user_id)
                .then()
                .log().body()
                .statusCode(404);

    }
}
