package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

public class TC_004_GET_All_Headers {

    @Test
    public void getHeadersDetails(){
        RestAssured.baseURI = "https://maps.googleapis.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/" +
                "xml?location=-33.8670522,151.1957362&radius=1500&" +
                "type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        //getting all the headers
        Headers headers =  response.headers();

        for (Header header : headers){
            System.out.println(header.getName()+"  :  "+header.getValue());
            System.out.println();
        }
    }
}
