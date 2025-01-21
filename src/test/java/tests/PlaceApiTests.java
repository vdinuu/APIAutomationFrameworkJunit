package tests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import place_api.CreatePlaceApi;
import place_api.DeletePlaceApi;
import place_api.GetPlaceApi;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlaceApiTests extends BaseTest{
    @Test
    @Order(0)
    public void addPlaceTest(){
        CreatePlaceApi createPlaceApi = new CreatePlaceApi();
        RequestSpecification requestSpecification = createPlaceApi.generateRequest("http://amazon.com", "French-IN");
        Response response = createPlaceApi.performPost(requestSpecification);
        assertEquals(200, response.getStatusCode());
        createPlaceApi.extractPlaceID(response);
    }

    @Test
    @Order(1)
    public void getPlaceTest(){
        GetPlaceApi getPlaceApi = new GetPlaceApi();
        RequestSpecification requestSpecification = getPlaceApi.generateRequest();
        Response response = getPlaceApi.performGet(requestSpecification);
        assertEquals(200, response.getStatusCode());
        assertEquals("http://amazon.com", getPlaceApi.getWebsite(response));
    }

    @Test
    @Order(2)
    public void deletePlaceTest(){
        DeletePlaceApi deletePlaceApi = new DeletePlaceApi();
        RequestSpecification requestSpecification = deletePlaceApi.generateRequest();
        Response response = deletePlaceApi.performPost(requestSpecification);
        assertEquals(200, response.getStatusCode());
        assertEquals("OK", deletePlaceApi.getStatusMessage(response));
    }

}
