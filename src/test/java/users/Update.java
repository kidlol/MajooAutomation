package users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Update
{
    @Test
    public void updateUser(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        //initiate JSON Object
        JSONObject params = new JSONObject();
        params.put("name", "Amber Heard");
        params.put("job","Actress Fucking with Aquaman");

        //add JSON Object to Body
        request.body(params.toString());

        //set Content Type
        request.header("Content-Type","application/json");
        Response response = request.put("/api/users/"+context.getAttribute("id_user"));
        String schemaPath = "src/resources/UpdateSchema.json";
        response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));
        System.out.printf(response.asString());
    }
}
