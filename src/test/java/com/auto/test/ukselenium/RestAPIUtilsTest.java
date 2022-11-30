package com.auto.test.ukselenium;

import com.auto.test.ukselenium.restUtilities.RestAssuredUtils;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class RestAPIUtilsTest {

    RestAssuredUtils restAssuredUtils = RestAssuredUtils.INSTANCE;
    String method;
    JSONObject jsonObject;

    @Test
    public void postCreateProduct() throws ParseException, IOException {

        this.jsonObject = readJsonAsObject("login.json");
        this.method = "POST";

        restAssuredUtils.getBuilder().setBaseUri("https://localhost:5001")
                .setBasePath("/api/Authenticate/Login")
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON);
        restAssuredUtils.setToken("Bearer " + restAssuredUtils.authenticate(method, jsonObject));

        System.out.println(restAssuredUtils.getToken());

        jsonObject = readJsonAsObjectByKey("ProductsDataset.json", "product1017");
        restAssuredUtils.getBuilder().setBasePath("/Product/Create");
        Assert.assertEquals(restAssuredUtils.executeWithJsonBody(method, jsonObject).getStatusCode(), 200);

    }

    public JSONObject readJsonAsObject(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        Object obj = parser.parse(new FileReader(file));
        return (JSONObject) obj;
    }

    public File readJsonAsFile(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        return file;
    }

    public JSONObject readJsonAsObjectByKey(String fileName, String key) throws IOException, ParseException {

        JSONParser parser = new JSONParser();

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file));

        return (JSONObject) jsonObject.get(key);

    }

    @Test
    public void test_temp() throws IOException, ParseException {
        this.jsonObject = readJsonAsObjectByKey("ProductsDataset.json", "product1002");
        System.out.println(this.jsonObject.toJSONString());

    }

}
