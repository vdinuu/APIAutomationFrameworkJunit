package place_api;

import data_generation.GeneratePayloadPojo;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RestUtils;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.dataMap;

public class CreatePlaceApi extends RestUtils {
    GeneratePayloadPojo generatePayloadPojo = new GeneratePayloadPojo();

    public RequestSpecification generateRequest(String website, String language) {
        return given()
                .spec(getRequestSpecification()).body(generatePayloadPojo.generateAddPlaceApiPayload(website, language));
    }
    public Response performPost(RequestSpecification requestSpecification){
        return performPostCall(requestSpecification, Endpoints.AddPlaceAPI);
    }

    public void extractPlaceID(Response response){
        dataMap.put("placeId", getValueFromResponse(response, "place_id"));
    }
}
