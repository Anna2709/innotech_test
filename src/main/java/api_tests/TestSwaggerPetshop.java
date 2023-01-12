package api_tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class TestSwaggerPetshop {
    public static final String URL = "https://petstore.swagger.io/v2/user/";
    public static final String USER_NAME = "UserIgnat";

    @Test()
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Description : 1. Create user. 2. Update user. 3. Get user information. 4. Delete cteated user.")
    public void testUser() {
        User user = new User(101, USER_NAME, "Ignat", "Ignatov", "Ignatov@gmail.com", "qwerty", "+79208880500", 1);
        User updatedUser = new User(101, USER_NAME, "NewIgnat", "NewIgnatov", "NewIgnatov@gmail.com", "qwerty", "+79208880500", 1);
        given()
                .body(user)
                .header("accept", " application/json")
                .header("Content-Type", "application/json")
                .when()
                .post(URL)
                .then()
                .log()
                .all()
                .statusCode(200);

        given()
                .body(updatedUser)
                .header("accept", " application/json")
                .header("Content-Type", "application/json")
                .when()
                .post(URL)
                .then()
                .log()
                .all()
                .statusCode(200);

        User updatingUser = when()
                .get(URL + USER_NAME)
                .then()
                .extract()
                .as(User.class);

        Assert.assertEquals(updatedUser.getFirstName(), updatingUser.getFirstName(), "User has not been found");
        Assert.assertEquals(updatedUser.getLastName(), updatingUser.getLastName(), "User has not been found");
        Assert.assertEquals(updatedUser.getEmail(), updatingUser.getEmail(), "User has not been found");
        Assert.assertEquals(updatedUser.getPassword(), updatingUser.getPassword(), "User has not been found");

        given()
                .header("accept", " application/json")
                .when()
                .delete(URL + USER_NAME)
                .then()
                .log()
                .all()
                .statusCode(200);

        Error errorMessage = when()
                .get(URL + USER_NAME)
                .then()
                .log()
                .all()
                .statusCode(404)
                .extract()
                .as(Error.class);

        Assert.assertEquals("User not found", errorMessage.getMessage(), "User has been found");
    }
}
