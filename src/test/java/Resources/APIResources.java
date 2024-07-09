package Resources;

public enum APIResources{

    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resouces;

    APIResources(String resouces){

        this.resouces = resouces;
    }

    public String getResouces( ) {
        return resouces;
    }

}
