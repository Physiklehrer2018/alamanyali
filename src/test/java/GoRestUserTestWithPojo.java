import POJO.GoRestUser;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class GoRestUserTestWithPojo {

    private RequestSpecification reqSpec;
    private GoRestUser user;

    @BeforeClass
    public void setup(){
        RestAssured.baseURI = "https://gorest.co.in";

        reqSpec = given()
                .log().body()
                .header("Authorization","Bearer 976e5357003cefdc2c1d66557ad1d5309e785bf4df3d1ccb2eb08a5406e9e673")
                .contentType(ContentType.JSON);

        user = new GoRestUser();
        user.setName("Physik Lehrerin");
        user.setEmail("fraulehrerin11@frau.com");
        user.setGender("female");
        user.setStatus("active");
    }

    @Test(priority = 1)
    public void newUserTest(){

        user.setId(given()
                .spec(reqSpec)
                .body(user)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(201)
                .body("name",equalTo(user.getName()))
                .extract().jsonPath().getString("id"));
    }

    @Test(priority = 2)
    public void newUserNegativeTest(){
        given()
                .spec(reqSpec)
                .body(user)
                .when()
                .post("/public/v2/users")
                .then()
                .log().body()
                .statusCode(422);
    }

    @Test(priority = 3)
    public void getUserTest(){
        given()
                .spec(reqSpec)
                .when()
                .get("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(user.getName()))
                .body("email", equalTo(user.getEmail()))
                .body("gender", equalTo(user.getGender()))
                .body("status", equalTo(user.getStatus()));

    }
    @Test(priority = 4)
    public void editUserTest(){

        HashMap<String,String> body = new HashMap<>();
        body.put("status","inactive");

        given()
                .spec(reqSpec)
                .body(body)
                .when()
                .put("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(200)
                .body("status", equalTo(body.get("status")));

    }

    @Test(priority = 5)
    public void deleteUserTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(204);
    }

    @Test(priority = 6)
    public void deleteUserNegativeTest(){
        given()
                .spec(reqSpec)
                .when()
                .delete("/public/v2/users/" + user.getId())
                .then()
                .log().body()
                .statusCode(404);
    }
}
