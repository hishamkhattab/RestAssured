package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

public class TC001_GET_Request {

    @Test
    public void getWeatherDetails(){

        //specify base url
        RestAssured.baseURI = "https://restcountries.eu/rest/v2/capital/";

        //create request object
        RequestSpecification httpRequest = RestAssured.given();

        //create response object to hold the response body in it
        Response response = httpRequest.request(Method.GET,"cairo");

        //print response to console
        String responseBody =  response.getBody().asString();
        System.out.println("Response body is: "+responseBody);

        //status code validation
        int statusCode = response.getStatusCode();
        Assert.assertEquals(200,statusCode);

        //status line verification
        String statusLine = response.getStatusLine();
        System.out.println("The response status line: "+statusLine);
        //Assert.assertEquals(" HTTP/1.1 200 OK",statusLine);
    }
}
