package register;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

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
        String schemaPath = "src/resources/RegisterSuccessfulSchema.json";
        response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
    }
}
