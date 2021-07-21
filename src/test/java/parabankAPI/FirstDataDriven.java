package parabankAPI;

import com.tngtech.java.junit.dataprovider.DataProvider;
import io.restassured.path.xml.XmlPath;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.ReadExcelData;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.IOException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstDataDriven {

    @ParameterizedTest
    @MethodSource("testData")
    public void dataDrivenTC(String customer_id, String firsName, String lastName,String street, String city, String state
                                , String zipCode, String phoneNumber, String ssn) {
/*        String xml = get("https://parabank.parasoft.com/parabank/services/bank/customers/"+customer_id+"/").andReturn().asString();
        XmlPath xmlPath = new XmlPath(xml).setRoot("customer");
        String xmlID = xmlPath.getString("id");
        String xmlFirstName = xmlPath.getString("firstname");
        String xmlLastName = xmlPath.getString("lastname");
        String xmlCity = xmlPath.getString("address.city");
        String xmlStreet = xmlPath.getString("address.street");
        String xmlState = xmlPath.getString("address.state");
        String xmlZipCode = xmlPath.getString("address.zipcode");
        String xmlPhoneNumber = xmlPath.getString("customer.phonenumber");
        String xmlSSN = xmlPath.getString("customer.ssn");*/

        given().get("https://parabank.parasoft.com/parabank/services/bank/customers/"+customer_id+"/")
                .then().assertThat().body("customer.id", equalTo(customer_id))
                .and().assertThat().body("customer.firstName", equalTo(firsName))
                .and().assertThat().body("customer.lastName", equalTo(lastName))
                .and().assertThat().body("customer.address.street", equalTo(street))
                .and().assertThat().body("customer.address.city", equalTo(city))
                .and().assertThat().body("customer.address.state", equalTo(state))
                .and().assertThat().body("customer.address.zipCode", equalTo(zipCode))
                .and().assertThat().body("customer.phoneNumber", equalTo(phoneNumber))
                .and().assertThat().body("customer.ssn", equalTo(ssn));
/*        Assert.assertEquals(xmlID, customer_id);
        Assert.assertEquals(xmlFirstName,firsName);
        Assert.assertEquals(xmlLastName,lastName);
        Assert.assertEquals(xmlCity,city);
        Assert.assertEquals(xmlStreet,street);
        Assert.assertEquals(xmlState,state);
        Assert.assertEquals(xmlZipCode,zipCode);
        Assert.assertEquals(xmlPhoneNumber,phoneNumber);
        Assert.assertEquals(xmlSSN,ssn);*/
    }
    @DataProvider
    public String[][] testData() throws IOException, InvalidFormatException {
        ReadExcelData read = new ReadExcelData();
        return read.readSheet();
    }

}
