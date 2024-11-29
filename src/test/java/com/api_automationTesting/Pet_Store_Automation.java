package com.api_automationTesting;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;


public class Pet_Store_Automation {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

	@Test(priority=1)
	public void AddPetTest() {
		String petBody = """
                {
                    "id": 10,
                    "name": "Rosie",
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
                .body("name", equalTo("Rosie"));
	}
	
	@Test(priority=2)
	 public void GetPetById() {
        int petId = 10;

        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/pet/" + petId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("id", equalTo(petId));
    }
	
	
	@Test(priority=3)
	public void FindPetsByStatus() {
        String status = "available"; // Can be "available", "pending", or "sold"

        Response res = given()
                .header("accept", "application/json")
                .queryParam("status", status)
                .when()
                .get(BASE_URL + "/pet/findByStatus");

        //res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("[0].status", equalTo(status));
    } 
	
	
	@Test(priority=4)
    public void UpdatePet() {
        String updatedPetBody = """
                {
                    "id": 10,
                    "name": "Rosie1",
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
                .body("name", equalTo("Rosie1"))
                .body("status", equalTo("sold"));
    }
	
	@Test(priority=5)
	public void DeletePetTest() {
        int petId = 10; // Replace with the pet ID to delete

        Response res = given()
                .header("accept", "application/json")
                .when()
                .delete(BASE_URL + "/pet/" + petId);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("message", equalTo(String.valueOf(petId)));
    }
	
	//Create user
	@Test(priority=6)
    public void CreateUser() {
        String userBody = """
                {
        		"id": 100,
        		"username": "Aditi_123",
        		"firstName": "Aditi",
        		"lastName": "Jadhav",
        		"email": "anjadhav123@gmail.com",
        		"password": "123456",
        		"phone": "8788999988",
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
	
	//Login User
	@Test(priority=7)
    public void LoginUser() {
        Response res = given()
                .header("accept", "application/json")
                .queryParam("username", "ashwini")
                .queryParam("password", "12345")
                .when()
                .get(BASE_URL + "/user/login");

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("message", containsString("logged in"));
    }

	//Get user by id
	@Test(priority=8)
    public void GetUserByUsername() {
        String username = "Aditi_123";

        Response res = given()
                .header("accept", "application/json")
                .when()
                .get(BASE_URL + "/user/" + username);

        res.prettyPrint();
        res.then().assertThat().statusCode(200)
                .body("username", equalTo(username));
    }
	
	//Update User
	 @Test(priority=9)
	    public void UpdateUser() {
	        String username = "Ashwini_123"; 

	        String updatedUserBody = """
	                {
	                    "id": 100,
	                    "username": "Ashwini_141",
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
	
	 //Delete User
	 @Test(priority=10)
	    public void DeleteUser() {
	        String username = "Ashwini_141";

	        Response res = given()
	                .header("accept", "application/json")
	                .when()
	                .delete(BASE_URL + "/user/" + username);

	        res.prettyPrint();
	        res.then().assertThat().statusCode(200);
	    }
	 
	 @Test(priority=11)
	    public void LogoutUser() {
	        Response res = given()
	                .header("accept", "application/json")
	                .when()
	                .get(BASE_URL + "/user/logout");

	        res.prettyPrint();
	        res.then().assertThat().statusCode(200);
	    }
	 
	 //Place order
	 @Test(priority=12)
	    public void PlaceOrder() {
	        String orderBody = """
	                {
	        		"id": 5,
	        		"petId": 108,
	        		"quantity": 3,
	        		"shipDate": "2024-11-22T14:23:14.863Z",
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
	 
	 //Get order by id
	 @Test(priority=13)
	    public void GetOrderById() {
	        int orderId = 1;

	        Response res = given()
	                .header("accept", "application/json")
	                .when()
	                .get(BASE_URL + "/store/order/" + orderId);

	        res.prettyPrint();
	        res.then().assertThat().statusCode(200)
	                .body("id", equalTo(orderId));
	    }
	 
	 //Delete order
	    @Test(priority=14)
	    public void DeleteOrder() {
	        int orderId = 8;

	        Response res = given()
	                .header("accept", "application/json")
	                .when()
	                .delete(BASE_URL + "/store/order/" + orderId);

	        res.prettyPrint();
	        res.then().assertThat().statusCode(200)
	                .body("message", equalTo(String.valueOf(orderId)));
	    }
	    
	    //Get inventory
	    @Test(priority=15)
	    public void GetInventory() {
	        Response res = given()
	                .header("accept", "application/json")
	                .when()
	                .get(BASE_URL + "/store/inventory");

	        res.prettyPrint();
	        res.then().assertThat().statusCode(200);
	    }
	 
}
