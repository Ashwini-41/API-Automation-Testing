package com.api_automationTesting;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import org.testng.Assert;

public class jsonServer {
	
	
	@Test(priority=1)
    public void GetPostsTest() {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json") 
            .when()
            .get("/posts") 
            .then()
            .extract().response(); 

        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200"); 
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response doesn't contain 'id' field");
    }
	
	@Test(priority=2)
	public void createPostTest() {
	
		RestAssured.baseURI = "http://localhost:3000";

        String requestBody = "{\n" +
                "    \"id\": \"2\",\n" +
                "    \"title\": \"Software testing\",\n" +
                "    \"author\": \"Ashwini\"\n" +
                "}";

        // Sending POST request to /posts
        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json") 
            .body(requestBody) 
            .when()
            .post("/posts") 
            .then()
            .extract().response(); 

        // Printing the response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201"); // Check status code for POST
        Assert.assertTrue(response.getBody().asString().contains("Ashwini"), "Response doesn't contain 'author: Ashwini'");
    
        response.prettyPrint();
	}
	
	@Test(priority=3)
	public void UpdatePostTest() {
		
		Response res = RestAssured
				.given()
				.header("Content-Type", "application/json") 
				.body("{\n" +
		                "    \"id\": \"1\",\n" +
		                "    \"title\": \"Automation testing\",\n" +
		                "    \"author\": \"Aditi\"\n" +
		                "}"
						)
				.when()
				.put("http://localhost:3000/posts/1");
		res.prettyPrint();
		
        System.out.println("Response Body: " + res.getBody().asString());
        Assert.assertEquals(res.getStatusCode(), 200);		
	}
	
	@Test(priority=4)
	public void DeletePostTest() {
		
		RestAssured.baseURI = "http://localhost:3000";
		
		Response res = RestAssured
				.given()
				.header("Content-Type", "application/json") 
				.when()
				.delete("/posts/2");
		res.prettyPrint();
		
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	
	//Get comments
	@Test(priority=5)
    public void GetCommentsTest() {
        RestAssured.baseURI = "http://localhost:3000";

        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json") 
            .when()
            .get("/comments") 
            .then()
            .extract().response(); 

        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200"); 
        Assert.assertTrue(response.getBody().asString().contains("id"), "Response doesn't contain 'id' field");
    }
	
	//Create Comments
	@Test(priority=6)
	public void createCommentTest() {
	
		RestAssured.baseURI = "http://localhost:3000";

        String requestBody = """ 
        		 {
                     "id": "4",
                     "body": "Well work",
                     "postId": 18
                 }
        		""";
        
        Response response = RestAssured
            .given()
            .header("Content-Type", "application/json") 
            .body(requestBody) 
            .when()
            .post("/comments") 
            .then()
            .extract().response(); 

        // Printing the response body
        System.out.println("Response Body: " + response.getBody().asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Status code is not 201"); 
    
        response.prettyPrint();
	}
	
	//update comments
	@Test(priority=7)
	public void UpdateCommentTest() {
		RestAssured.baseURI = "http://localhost:3000";
		
		String commentBody = """
				{
              "id": "2",
              "body": "Comment here",
              "postId": 2
                }
				""";

		Response res = given()
				.header("Content-Type", "application/json") 
				.when()
				.body(commentBody)
				.put("/comments/2");
		res.prettyPrint();
        res.then().assertThat().statusCode(200);
				
	}
	
	//Delete Comments
	@Test(priority=8)
	public void DeleteCommentTest() {
		RestAssured.baseURI = "http://localhost:3000";

		Response res = given()
				.header("Content-Type", "application/json") 
				.when()
				.delete("/comments/4");
		res.prettyPrint();
        res.then().assertThat().statusCode(200);
				
	}
	
	//Get profile method
	@Test(priority=9)
	public void GetProfileTest() {
		RestAssured.baseURI = "http://localhost:3000";

		Response res = given()
				.header("Content-Type", "application/json") 
				.when()
				.get("/profile");
		//res.prettyPrint();
        System.out.println("Response Body: " + res.getBody().asString());

        res.then().assertThat().statusCode(200);
				
	}
	
  // create new profile
	@Test(priority=10)
	public void CreateProfileTest() {
		RestAssured.baseURI = "http://localhost:3000";
		String profileBody = """ 
				 {
                 "id": 1,
				"name": "Shraddha",
				"roll no": 10,
				"branch": "Tech eng"
			     }
				""";

		Response res = given()
				.header("Content-Type", "application/json") 
				.body(profileBody)
				.when()
				.post("/profile");
		//res.prettyPrint();
        System.out.println("Response Body: " + res.getBody().asString());

        res.then().assertThat().statusCode(201);
				
	}
	
	//update profile
	@Test(priority=11)
	public void UpdateProfileTest() {
		RestAssured.baseURI = "http://localhost:3000";
		
		String commentBody = """
				{
    "id": 1,
    "name": "Shraddha",
    "roll no": 108,
    "branch": "IT eng"
}
				""";

		Response res = given()
				.header("Content-Type", "application/json") 
				.when()
				.body(commentBody)
				.put("/profile/1");
		res.prettyPrint();
      System.out.println("Response Body Of Update Profile: " + res.getBody().asString());

        res.then().assertThat().statusCode(200);
				
	}
	
	//Delete Profile
	@Test(priority=12)
	public void DeleteProfileTest() {
		RestAssured.baseURI = "http://localhost:3000";

		Response res = given()
				.header("Content-Type", "application/json") 
				.when()
				.delete("/profile/1");
		res.prettyPrint();
		
        res.then().assertThat().statusCode(200);
				
	}
	
	

	
}
