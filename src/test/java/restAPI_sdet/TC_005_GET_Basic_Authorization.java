package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

public class TC_005_GET_Basic_Authorization {

    @Test
    public void authorizationTest(){

        RestAssured.baseURI = "https://postman-echo.com/basic-auth";


        PreemptiveBasicAuthScheme basicAuth = new PreemptiveBasicAuthScheme();
        basicAuth.setUserName("postman");
        basicAuth.setPassword("password");

        RestAssured.authentication = basicAuth;


        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET);

        Assert.assertEquals(200,response.statusCode());

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(true,jsonPath.get("authenticated"));
    }
}
