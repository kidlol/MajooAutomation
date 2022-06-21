package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

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
        params.put("name", "Ambere Heard");
        params.put("Job","Actrist Fucking with Aquaman");

        //add JSON Object to Body
        request.body(params.toString());

        //set Content Type
        request.header("Content_Type","application/json");
        Response response = request.put("/api/users/"+context.getAttribute("id_user"));
        response.then().assertThat().statusCode(200);
        System.out.printf(response.asString());
    }
}
