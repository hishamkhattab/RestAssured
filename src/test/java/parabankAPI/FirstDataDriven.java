package parabankAPI;

import com.tngtech.java.junit.dataprovider.DataProvider;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Assert;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.XLUtility;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FirstDataDriven {

    @ParameterizedTest
    @MethodSource("testData")
    public void dataDrivenTC(String customer_id, String firsName, String lastName,String street, String city, String state,String zipCode, String phoneNumber, String ssn) {
        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services/bank/customers";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.request(Method.GET,"/"+customer_id+"/");

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.get(".id"), customer_id);
        assertEquals(jsonPath.get(".firstName"), firsName);
        assertEquals(jsonPath.get(".lastName"), lastName);
        assertEquals(jsonPath.get(".address.street"), street);
        assertEquals(jsonPath.get(".address.city"), city);
        assertEquals(jsonPath.get(".address.state"), state);
        assertEquals(jsonPath.get(".address.zipCode"), zipCode);
        assertEquals(jsonPath.get(".phoneNumber"), phoneNumber);
        assertEquals(jsonPath.get(".ssn"),ssn);
    }


    @DataProvider
    public Object[][] testData() throws IOException, InvalidFormatException {

        String path = "D:\\WorkSpace\\rest_assured_BDD\\src\\test\\resources\\Book1.xlsx";
        XLUtility util = new XLUtility(path);
        int rowNumber = util.getRowCount("sheet1");
        int colNumber = util.getCellCount("sheet1",1);
        Object data[][] = new Object[rowNumber][colNumber];
        for (int r=1; r<=rowNumber; r++){
            for (int c=0; c<colNumber; c++){
                data[r-1][c] = util.getCellData("sheet1",r,c);
            }
        }
        return data;
    }

}
