import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SecondTest
{
    @Test
    public void second()
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        JSONObject bodyReq = new JSONObject();
        bodyReq.put("name","Jhonny Deep");
        bodyReq.put("job","Actor Movie");

        request.header("Content-type", "Application/json");
        request.body(bodyReq.toString());

        Response response = request.post("/api/users/");
        response.then().assertThat().statusCode(201).body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/CreateUserSchema.json")));
    }
}
