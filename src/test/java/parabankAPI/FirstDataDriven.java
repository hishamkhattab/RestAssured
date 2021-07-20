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

import java.io.IOException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstDataDriven {

    @ParameterizedTest
    @MethodSource("testData")
    public void dataDrivenTC(String customer_id, String firsName, String lastName, String city, String state
                                ,String street, String zipCode, String phoneNumber, String ssn) {
        String xml = get("https://parabank.parasoft.com/parabank/services/bank/customers/"+customer_id+"/").andReturn().asString();
        XmlPath xmlPath = new XmlPath(xml).setRoot("customer");
        String xmlID = xmlPath.getString("id");
        String xmlFirstName = xmlPath.getString("firstname");
        String xmlLastName = xmlPath.getString("lastname");
        String xmlCity = xmlPath.getString("city");
        String xmlStreet = xmlPath.getString("street");
        String xmlState = xmlPath.getString("state");
        String xmlZipCode = xmlPath.getString("zipcode");
        String xmlPhoneNumber = xmlPath.getString("phonenumber");
        String xmlSSN = xmlPath.getString("ssn");

        Assert.assertEquals(xmlID, customer_id);
        //Assert.assertEquals(xmlFirstName, firsName);
    }
    @DataProvider
    public String[][] testData() throws IOException, InvalidFormatException {
        ReadExcelData read = new ReadExcelData();
        return read.readSheet();
    }

}
