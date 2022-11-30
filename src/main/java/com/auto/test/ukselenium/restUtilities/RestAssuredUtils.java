package com.auto.test.ukselenium.restUtilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.util.Objects;

public enum RestAssuredUtils {

    INSTANCE;
    String token;
    private RequestSpecBuilder builder = new RequestSpecBuilder();

    public RequestSpecBuilder getBuilder() {
        return builder;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        this.builder.addHeader("Authorization", token);

    }

    public String authenticate(String method, Object body) {
        builder.setBody(body);
        return Objects.requireNonNull(executeAPI(method)).getBody().jsonPath().get("token");
    }

    private ResponseOptions<Response> executeAPI(String method) {

        RequestSpecification request = RestAssured.given();
        request.spec(builder.build());

        if(method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post();
        else if(method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete();
        else if(method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get();
        return null;
    }

    public ResponseOptions<Response> executeWithJsonBody(String method, JSONObject jsonObject) {
        builder.setBody(jsonObject);
        return executeAPI(method);
    }
}
