package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

public class TC_003_GoogleMapAPI_ValidatingHeaders {

    @Test
    public void validateHeaders(){
        RestAssured.baseURI = ("https://maps.googleapis.com");

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/" +
                "xml?location=-33.8670522,151.1957362&radius=1500&" +
                "type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        String responseBody = response.getBody().asString();
        System.out.println("The response body is: "+responseBody);

        //Capture response header
        String contentType =  response.header("Content-Type");
        Assert.assertEquals("application/xml; charset=UTF-8",contentType);

        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals("gzip",contentEncoding);
    }
}
