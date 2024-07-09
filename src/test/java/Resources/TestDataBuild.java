package Resources;

import PojoClasses.AddPlace;
import PojoClasses.Location;

import java.util.ArrayList;

public class TestDataBuild {

    public static  AddPlace addPlacepayload(String name, String language, String address){

        Location locationdetails = new Location(-38.383494, 33.427362);
        ArrayList<String> typedetails = new ArrayList<String>();
        typedetails.add("Ayalesbury");
        typedetails.add("BerryFields");
        AddPlace AddplaceBody = new AddPlace(locationdetails, 50, name, "(+91) 879 079 8150", address,
                typedetails, "http://blisssoftware.com", language);

        return AddplaceBody;
    }

    public static String deleteApiPayload(String placeid){

        return"{\n" +
                "\n" +
                "    \"place_id\":\""+placeid+"\"\n" +
                "}";
    }

}
