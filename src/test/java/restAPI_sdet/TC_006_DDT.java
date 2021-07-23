package restAPI_sdet;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.XLUtility;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TC_006_DDT {

    @Test(dataProvider = "empDataProvider")
    public void addNewEmployee(String name, String salary, String age){
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject jsonBody = new JSONObject();
       /* jsonBody.put("name","johnxyz");
        jsonBody.put("salary","20000");
        jsonBody.put("age","30");
*/
        jsonBody.put("name",name);
        jsonBody.put("salary",salary);
        jsonBody.put("age",age);


        httpRequest.header("Content-Type","application/json");
        httpRequest.body(jsonBody.toJSONString());


        Response response = httpRequest.request(Method.POST,"/create");

        String responseBody = response.getBody().asString();

        System.out.println("************************************************************");
        System.out.println(responseBody);
        System.out.println("************************************************************");

        JsonPath path = response.jsonPath();
        Headers headers = response.getHeaders();
        for (Header header: headers){
            System.out.println("*****************");
            System.out.println(header);
        }

        Assert.assertEquals(path.get(".data.name"),name);
        Assert.assertEquals(path.get(".data.salary"),salary);
        Assert.assertEquals(path.get(".data.age"),age);
        //Assert.assertEquals(201,response.statusCode());
    }

    @DataProvider(name = "empDataProvider")
    Object[][] getEmpData() throws IOException {
        String path = "D:\\WorkSpace\\rest_assured_BDD\\src\\test\\resources\\employes.xlsx";
        XLUtility util = new XLUtility(path);

        int rowNum = util.getRowCount("Sheet1");
        int colNum = util.getCellCount("sheet1",1);

        Object[][] empData = new Object[rowNum][colNum];
        for (int i = 1; i <= rowNum; i++){
            for (int j = 0; j < colNum; j++){
                empData[ i - 1 ][j] = util.getCellData("sheet1",i,j);
            }
        }


        return empData;
/*
        return new String[][]{{"abc123", "300", "40"}, {"xyz123", "300", "40"}, {"ab23", "300", "40"},
                              {"abc3", "300", "40"}, {"a23", "300", "40"}, {"b3", "300", "40"}};
*/
    }


    @ParameterizedTest
    @MethodSource("getEmpData")
    public void addNewEmpJunit(String name, String salary, String age){
            RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

            RequestSpecification httpRequest = RestAssured.given();

            JSONObject jsonBody = new JSONObject();
       /* jsonBody.put("name","johnxyz");
        jsonBody.put("salary","20000");
        jsonBody.put("age","30");
*/
            jsonBody.put("name",name);
            jsonBody.put("salary",salary);
            jsonBody.put("age",age);


            httpRequest.header("Content-Type","application/json");
            httpRequest.body(jsonBody.toJSONString());


            Response response = httpRequest.request(Method.POST,"/create");

            String responseBody = response.getBody().asString();

            System.out.println("************************************************************");
            System.out.println(responseBody);
            System.out.println("************************************************************");

            JsonPath path = response.jsonPath();
            Headers headers = response.getHeaders();
            for (Header header: headers){
                System.out.println("*****************");
                System.out.println(header);
            }

            Assert.assertEquals(path.get(".data.name"),name);
            Assert.assertEquals(path.get(".data.salary"),salary);
            Assert.assertEquals(path.get(".data.age"),age);
            //Assert.assertEquals(201,response.statusCode());
        }


}
