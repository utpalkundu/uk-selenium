package com.auto.test.ukselenium;

import com.auto.test.ukselenium.restUtilities.RestAssuredHelper;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.*;

public class RestAPIHelperTest {

    @Test
    public void postCreateProduct() throws ParseException, IOException {

        JSONObject jsonObject = readJsonBodyAsObject("login.json");

        RestAssuredHelper restAssuredHelper = new RestAssuredHelper("/api/Authenticate/Login", "POST", null);
        String token = restAssuredHelper.authenticate(jsonObject);
        System.out.println(token);

        jsonObject = readJsonBodyAsObject("product.json");
        RestAssuredHelper restAssuredHelper2 = new RestAssuredHelper("/Product/Create", "POST", token);
        System.out.println(restAssuredHelper2.ExecuteWithJsonBody(jsonObject).getStatusCode());

    }

    @Test
    public void postCreateProduct2() throws ParseException, IOException {


        JSONObject jsonObject = readJsonBodyAsObject("login.json");

        RestAssuredHelper restAssuredHelper = new RestAssuredHelper("/api/Authenticate/Login", "POST", null);
        String token = restAssuredHelper.authenticate(jsonObject);
        System.out.println(token);

        jsonObject = readJsonBodyAsObject("product.json");
        RestAssuredHelper restAssuredHelper2 = new RestAssuredHelper("/Product/Create", "POST", token);
        System.out.println(restAssuredHelper2.ExecuteWithJsonBody(jsonObject).getStatusCode());

    }

    @Test
    public void postCreateProductBDDStyle() throws IOException, ParseException {

        given().relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .baseUri("https://localhost:5001")
                .basePath("/api/Authenticate/Login")
                .body(readJsonBodyAsFile("login.json"))
                .when().post()
                .then().statusCode(200)
                .log().body();

    }

    public JSONObject readJsonBodyAsObject(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Object obj = parser.parse(new FileReader(file));
        return (JSONObject)obj;
    }

    public File readJsonBodyAsFile(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        return file;
    }


}
