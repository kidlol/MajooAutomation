package auth;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class login_unsuccessful
{
    @Test
    public void loginUnsuccessful(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("email", "kobe@getnada.com");

        //add JSON Object to Body
        request.body(params.toString());

        //set Content Type
        request.header("Content-Type","application/json");
        Response response = request.post("/api/login");
        String schemaPath = "src/resources/LoginUnsuccessSchema.json";
        response.then().assertThat().statusCode(400).body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
        System.out.printf(response.asString());
    }
}
