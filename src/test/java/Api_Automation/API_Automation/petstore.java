package Api_Automation.API_Automation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class petstore {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    @Test
    public void createPet() {
        String petBody = """
                {
                    "id": 12345,
                    "name": "Rover",
                    "status": "available"
                }
                """;

        Response res = given()
                .header("Content-Type", "application/json")
                .body(petBody)
                .when()
                .post(BASE_URL + "/pet");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("name", equalTo("Rover"));
    }

    @Test
    public void GetPetById() {
        int petId = 12345;

        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/pet/" + petId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("id", equalTo(petId));
    }

    @Test
    public void UpdatePet() {
        String updatedPetBody = """
                {
                    "id": 1234,
                    "name": "RoverUpdated",
                    "status": "sold"
                }
                """;

        Response res = given()
                .header("Content-Type", "application/json")
                .body(updatedPetBody)
                .when()
                .put(BASE_URL + "/pet");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("name", equalTo("RoverUpdated"))
                .body("status", equalTo("sold"));
    }

    @Test
    public void DeletePet() {
        int petId = 12345; // Replace with the pet ID to delete

        Response res = given()
                .header("accept", "application/json")
                .when()
                .delete(BASE_URL + "/pet/" + petId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("message", equalTo(String.valueOf(petId)));
    }

    @Test
    public void FindPetsByStatus() {
        String status = "available"; // Can be "available", "pending", or "sold"

        Response res = given()
                .header("accept", "application/json")
                .queryParam("status", status)
                .when()
                .get(BASE_URL + "/pet/findByStatus");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("[0].status", equalTo(status));
    }

    @Test
    public void CreateUser() {
        String userBody = """
                {
                    "id": 101,
                    "username": "TestUser",
                    "firstName": "John",
                    "lastName": "Doe",
                    "email": "johndoe@example.com",
                    "password": "password123",
                    "phone": "1234567890",
                    "userStatus": 1
                }
                """;

        Response res = given()
                .header("Content-Type", "application/json")
                .body(userBody)
                .when()
                .post(BASE_URL + "/user");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void GetUserByUsername() {
        String username = "TestUser";

        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/user/" + username);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("username", equalTo(username));
    }

    @Test
    public void UpdateUser() {
        String username = "testUser"; // Replace with the username to update

        String updatedUserBody = """
                {
                    "id": 101,
                    "username": "testUser",
                    "firstName": "Jane",
                    "lastName": "Smith",
                    "email": "janesmith@example.com",
                    "password": "newpassword123",
                    "phone": "9876543210",
                    "userStatus": 1
                }
                """;

        Response res = given()
                .header("Content-Type", "application/json")
                .body(updatedUserBody)
                .when()
                .put(BASE_URL + "/user/" + username);

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void DeleteUser() {
        String username = "TestUser";

        Response res = given()
                .header("accept", "application/json")
                .when()
                .delete(BASE_URL + "/user/" + username);

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }

    @Test
    public void LoginUser() {
        Response res = given()
                .header("accept", "application/json")
                .queryParam("username", "testUser")
                .queryParam("password", "password123")
                .when()
                .get(BASE_URL + "/user/login");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("message", containsString("logged in"));
    }

    @Test
    public void LogoutUser() {
        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/user/logout");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }


    @Test
    public void PlaceOrder() {
        String orderBody = """
                {
                    "id": 101,
                    "petId": 1234,
                    "quantity": 2,
                    "shipDate": "2024-11-19T14:00:00.000Z",
                    "status": "placed",
                    "complete": true
                }
                """;

        Response res = given()
                .header("Content-Type", "application/json")
                .body(orderBody)
                .when()
                .post(BASE_URL + "/store/order");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("status", equalTo("placed"));
    }

    @Test
    public void GetOrderById() {
        int orderId = 101;

        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/store/order/" + orderId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("id", equalTo(orderId));
    }

    @Test
    public void DeleteOrder() {
        int orderId = 101;

        Response res = given()
                .header("accept", "application/json")
                .when()
                .delete(BASE_URL + "/store/order/" + orderId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("message", equalTo(String.valueOf(orderId)));
    }

    @Test
    public void GetInventory() {
        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/store/inventory");

        res.prettyPrint();
        res.then().assertThat().statusCode(200);
    }
}