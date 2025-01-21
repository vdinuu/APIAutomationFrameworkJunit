package place_api;

import data_generation.GeneratePayloadPojo;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RestUtils;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.dataMap;

public class DeletePlaceApi extends RestUtils {

    GeneratePayloadPojo generatePayloadPojo = new GeneratePayloadPojo();
    public RequestSpecification generateRequest() {
        return given().spec(getRequestSpecification())
                .body(generatePayloadPojo.getDeletePlaceApiPayload(dataMap.get("placeId").toString()));
    }

    public Response performPost(RequestSpecification requestSpecification){
        return performPostCall(requestSpecification, Endpoints.DeletePlaceAPI);
    }
    public String getStatusMessage(Response response){
        return getValueFromResponse(response, "status");
    }
}
