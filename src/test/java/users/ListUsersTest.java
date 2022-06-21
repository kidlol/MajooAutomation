package users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ListUsersTest
{
    @Test
    public void successGetListUsers(ITestContext context)
    {
        RestAssured.baseURI = "https://reqres.in/";
        RequestSpecification request = given();

        request.param("page",2);
        Response response = request.get("/api/users");
        response.then().assertThat().statusCode(200);

        System.out.println(response.jsonPath().getString("data[1].id"));
        context.setAttribute("id_user",response.jsonPath().getInt("data[1].id"));
    }
}
