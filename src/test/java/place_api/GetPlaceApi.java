package place_api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.RestUtils;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.dataMap;

public class GetPlaceApi extends RestUtils {

    public RequestSpecification generateRequest() {
        return given()
                .spec(getRequestSpecification())
                .queryParam("place_id", dataMap.get("placeId"));
    }
    public Response performGet(RequestSpecification requestSpecification){
        return performGetCall(requestSpecification, Endpoints.GetPlaceAPI);
    }
    public String getWebsite(Response response){
        return getValueFromResponse(response, "website");
    }
}
