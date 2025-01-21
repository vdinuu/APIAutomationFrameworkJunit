package place_api;

public enum Endpoints {
    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");
    private String endpointDetails;

    Endpoints(String endpointDetails){
        this.endpointDetails = endpointDetails;
    }

    public String getEndpoint(){
        return endpointDetails;
    }


}
