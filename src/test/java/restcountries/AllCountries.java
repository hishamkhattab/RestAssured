package restcountries;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class AllCountries {

    @Test
    public void validateResponseCode(){
        given().get("https://restcountries.eu/rest/v2/all")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void validateResponseCode_anotherWay(){
        Response restAssured = RestAssured.get("https://restcountries.eu/rest/v2/all");

        Assert.assertEquals(restAssured.statusCode(),200);
    }
}
