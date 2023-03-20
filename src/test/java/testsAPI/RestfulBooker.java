package testsAPI;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class RestfulBooker {

    public static int bookingId;
    public static String token;
    @Test(priority = 1)
    public void createToken(){
        String requestBody = "{\n" + "\"username\": \"admin\",\n" + "\"password\": \"password123\"\n" + "}";
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract().response();
        token = response.jsonPath().getString("token");

    }
    @Test(priority = 2)
    public void createBooking(){
        String requestBody = "{\n"
                + "    \"firstname\": \"Jim\",\n"
                + "    \"lastname\": \"Brown\",\n"
                + "    \"totalprice\": 111,\n"
                + "    \"depositpaid\": true,\n"
                + "    \"bookingdates\": {\n"
                + "    \"checkin\": \"2018-01-01\",\n"
                + "    \"checkout\": \"2019-01-01\"\n"
                + "    },\n"
                + "    \"additionalneeds\": \"Breakfast\"\n"
                + "}";
    Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(200).extract().response();
    bookingId = response.jsonPath().getInt("bookingid");
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .when()
                .get()
                .then()
                .body("firstname", equalTo("Jim"))
                .body("lastname", equalTo("Brown"))
                .body("totalprice", equalTo(111))
                .body("depositpaid", equalTo(true))
                .body("bookingdates.checkin", equalTo("2018-01-01"))
                .body("bookingdates.checkout", equalTo("2019-01-01"))
                .body("additionalneeds", equalTo("Breakfast"));
    }
    @Test(priority = 3)
    public void updateBooking(){
        String requestBody = "{\"totalprice\": 999}";
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .header("Content-Type", "application/json")
                .cookie("token", token)
                .body(requestBody)
                .when()
                .patch()
                .then()
                .statusCode(200);
    }
    @Test(priority = 4)
    public void getUpdatedBooking(){
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();
        int totalprice = response.jsonPath().getInt("totalprice");
        Assert.assertEquals(totalprice,999);
    }
    @Test(priority = 5)
    public void getAllBookings(){
        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().response();
        List<Integer> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertTrue(bookingIds.contains(bookingId));
    }

    @Test(priority = 6)
    public void deleteBooking(){
        RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking/" + bookingId)
                .cookie("token", token)
                .when()
                .delete()
                .then()
                .statusCode(201);
    }
}
