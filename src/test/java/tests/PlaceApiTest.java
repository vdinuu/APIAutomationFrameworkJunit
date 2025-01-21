package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import place_api.CreatePlaceApi;
import place_api.DeletePlaceApi;
import place_api.GetPlaceApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaceApiTest extends BaseTest{
    @Test
    @Order(0)
    @Tag(value = "smoke")
    @Tag(value = "regression")
    @Epic("PlaceAPI Validations")
    @Feature("AddPlaceAPI Validations")
    @Description("Verify AddPlaceAPI")
    public void addPlaceTest(){
        CreatePlaceApi createPlaceApi = new CreatePlaceApi();
        RequestSpecification requestSpecification = createPlaceApi.generateRequest("http://amazon.com", "French-IN");
        Response response = createPlaceApi.performPost(requestSpecification);
        assertEquals(200, response.getStatusCode());
        createPlaceApi.extractPlaceID(response);
    }

    @Test
    @Order(1)
    @Tag(value = "regression")
    @Epic("PlaceAPI Validations")
    @Feature("GetPlaceAPI Validations")
    @Description("Verify GetPlaceAPI")
    public void getPlaceTest(){
        GetPlaceApi getPlaceApi = new GetPlaceApi();
        RequestSpecification requestSpecification = getPlaceApi.generateRequest();
        Response response = getPlaceApi.performGet(requestSpecification);
        assertEquals(200, response.getStatusCode());
        assertEquals("http://amazon.com", getPlaceApi.getWebsite(response));
    }

    @Test
    @Order(2)
    @Tag(value = "regression")
    @Epic("PlaceAPI Validations")
    @Feature("DeletePlaceAPI Validations")
    @Description("Verify DeletePlaceAPI")
    public void deletePlaceTest(){
        DeletePlaceApi deletePlaceApi = new DeletePlaceApi();
        RequestSpecification requestSpecification = deletePlaceApi.generateRequest();
        Response response = deletePlaceApi.performPost(requestSpecification);
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", deletePlaceApi.getStatusMessage(response));
    }

}
