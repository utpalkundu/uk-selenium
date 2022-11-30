package com.auto.test.ukselenium;

import com.auto.test.ukselenium.common.JsonUtils;
import com.auto.test.ukselenium.restUtilities.RestAssuredUtilsSpring;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

@SpringBootTest
public class RestAssuredUtilsSpringTest extends SpringBaseTest{

    @Autowired
    RestAssuredUtilsSpring restAssuredUtilsSpring;

    @Autowired
    JsonUtils jsonUtils;

    private JSONObject jsonObject;
    private String method;

    @Test
    public void postCreateProduct() throws ParseException, IOException {

        this.jsonObject = jsonUtils.readJsonAsObject("login.json");
        this.method = "POST";

        restAssuredUtilsSpring.getBuilder().setBaseUri(restAssuredUtilsSpring.getBaseURL())
                .setBasePath("/api/Authenticate/Login")
                .setRelaxedHTTPSValidation()
                .setContentType(ContentType.JSON);

        restAssuredUtilsSpring.setToken("Bearer " + restAssuredUtilsSpring.authenticate(this.method, this.jsonObject));

        System.out.println(restAssuredUtilsSpring.getToken());

        this.jsonObject = jsonUtils.readJsonAsObjectByKey("ProductsDataset.json", "product1016");
        restAssuredUtilsSpring.getBuilder().setBasePath("/Product/Create");
         Assert.assertEquals(restAssuredUtilsSpring.executeWithJsonBody(method, jsonObject).getStatusCode(), 200);

    }
}
