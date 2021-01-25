package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

// based on: https://github.com/balamutsj/restAssuredTraining
public class TestGetUserName {

	private final String baseUrl = "http://petstore.swagger.io/v2";
	private final String urlToUser = "/user/{username}";

	@Test(enabled = false)
	public void getUserLastName() {

		given().pathParam("username", "IgorSanzh").when().get(baseUrl + urlToUser)
				.then().log().all().body("lastName", equalTo("Sanzh"));
	}

	// see also:
	// http://toolsqa.com/rest-assured/read-response-body-using-rest-assured/
}
