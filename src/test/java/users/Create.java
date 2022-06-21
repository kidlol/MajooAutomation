package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Create
{
    @Test
    public void createUser(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("name", "Jhonny Deep");
        params.put("Job","Actor");

        //add JSON Object to Body
        request.body(params.toString());

        //set Content Type
        request.header("Content_Type","application/json");
        Response response = request.post("/api/users/");
        response.then().assertThat().statusCode(201);
        System.out.printf(response.asString());
    }
}
