package parabankAPI;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ResponseData {

    @Test
    public void validateResponseData(){
        given().get("https://parabank.parasoft.com/parabank/services/bank/customers/12212")
                .then().assertThat().body("customer.id", equalTo("12212"))
                .and().assertThat().body("customer.firstName", equalTo("John"))
                .and().assertThat().body("customer.lastName", equalTo("Smith"))
                .and().assertThat().body("customer.address.street", equalTo("1431 Main St"))
                .and().assertThat().body("customer.address.city", equalTo("Beverly Hills"))
                .and().assertThat().body("customer.address.state", equalTo("CA"))
                .and().assertThat().body("customer.address.zipCode", equalTo("90210"))
                .and().assertThat().body("customer.phoneNumber", equalTo("310-447-4121"))
                .and().assertThat().body("customer.ssn", equalTo("622-11-9999"));
    }
}
