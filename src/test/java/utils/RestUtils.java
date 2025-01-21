package utils;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import place_api.Endpoints;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static tests.BaseTest.properties;

public class RestUtils {
    public static RequestSpecification requestSpec;

    public RequestSpecification getRequestSpecification(){
        if(null == requestSpec) {
            PrintStream log;
            try {
                log = new PrintStream(new FileOutputStream("log.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            requestSpec = new RequestSpecBuilder().setBaseUri(properties.getProperty("baseUrl"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .addFilter(new AllureRestAssured())
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return requestSpec;
    }

    public String getValueFromResponse(Response response, String jsonPath){
        JsonPath jsPath = new JsonPath(response.asString());
        return jsPath.get(jsonPath);
    }
    public Response performPostCall(RequestSpecification requestSpecification, Endpoints endpoints){
        return requestSpecification
                .when()
                .post(endpoints.getEndpoint());
    }

    public Response performGetCall(RequestSpecification requestSpecification, Endpoints endpoint){
        return requestSpecification
                .when()
                .get(endpoint.getEndpoint());
    }
}
