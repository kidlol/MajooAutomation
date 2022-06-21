package users;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SingleUserTest
{
    @Test
    public void successGetSingleUser(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        Response response = request.get("/api/users/"+context.getAttribute("id_user"));
        String schemaPath = "src/resources/SingleUserSchema.json";
        response.then().assertThat().statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));

        System.out.printf(response.asString());
    }
}
