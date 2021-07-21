package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TC002_POSTRequest {
    @Test
    public void createNewEntity(){
        RestAssured.baseURI = "https://reqres.in/api/users";

        //request object
        RequestSpecification httpRequest = RestAssured.given();

            //create a request body
        JSONObject requestParameters = new JSONObject();
        requestParameters.put("name", "morpheus");
        requestParameters.put("job", "leader");

            //adding header
        httpRequest.header("Content-Type","application/json");

            //attach the request body and header to the http request
        httpRequest.body(requestParameters.toJSONString());

        //response object
        Response response = httpRequest.request(Method.POST,"/CREATE");

        //verification

        assertEquals(201,response.getStatusCode());

        String status =  response.jsonPath().get("name");

        assertEquals("morpheus",status);

    }
}
