package register;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class register_successful
{
    @Test
    public void registerSuccessful(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("email", "eve.holt@reqres.in");
        params.put("password","pistol");

        //add JSON Object to Body
        request.body(params.toString());

        //set Content Type
        request.header("Content-Type","application/json");
        Response response = request.post("/api/register/");
        System.out.printf(response.asString());
        response.then().assertThat().statusCode(200);
    }
}
