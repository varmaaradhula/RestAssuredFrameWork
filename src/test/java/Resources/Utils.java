package Resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification Requestdetails;
    public static ResponseSpecification responsespec;

    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log;
        if (Requestdetails == null) {
            //PrintStream log;
            try {
                log = new PrintStream(new FileOutputStream("logging.txt"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Requestdetails = new RequestSpecBuilder().setBaseUri(getGlobalproperties("BaseUri")).addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .addHeader("Content-Type", "application/json").build();

            return Requestdetails;
        }
        return Requestdetails;
    }

    public ResponseSpecification responseSpecification(){

        responsespec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        return responsespec;

    }

    public static String getGlobalproperties(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/Globalproperties.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public static String getJsonPath(Response response, String key){

        String res = response.asString();

        JsonPath js = new JsonPath(res);

        return js.getString(key);

    }
}
