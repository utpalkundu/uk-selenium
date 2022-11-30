package com.auto.test.ukselenium.restUtilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@Component
public class RestAssuredUtilsSpring {

    @Autowired
    public RequestSpecBuilder builder;

    @Value("${microservicebase.uri:https://localhost:5001}")
    private String baseURL;

    private String token;

    public RequestSpecBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(RequestSpecBuilder builder) {
        this.builder = builder;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        this.builder.addHeader("Authorization", this.token);
    }

    public String authenticate(String method, Object body) {
        this.builder.setBody(body);
        return Objects.requireNonNull(executeAPI(method)).getBody().jsonPath().get("token");
    }

    private ResponseOptions<Response> executeAPI(String method) {

        RequestSpecification request = RestAssured.given();
        request.spec(this.builder.build());

        if(method.equalsIgnoreCase(APIConstant.ApiMethods.POST))
            return request.post();
        else if(method.equalsIgnoreCase(APIConstant.ApiMethods.DELETE))
            return request.delete();
        else if(method.equalsIgnoreCase(APIConstant.ApiMethods.GET))
            return request.get();
        return null;
    }

    public ResponseOptions<Response> executeWithJsonBody(String method, JSONObject jsonObject) {
        this.builder.setBody(jsonObject);
        return executeAPI(method);
    }

}
