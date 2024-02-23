package core.utils.TestRailConnector;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import core.utils.AndroidCore.AndroidDriverSetup;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.remote.SessionId;

import java.io.File;

public class BsApiHelper {
    protected static final String API_URL = "https://" + AndroidDriverSetup.AUTOMATE_USERNAME + ":" + AndroidDriverSetup.AUTOMATE_ACCESS_KEY + "@api-cloud.browserstack.com";

    public static void updateTestStatus(Scenario scenario, SessionId id) {
        String successMessage = "All steps passed!";
        String errorMessage = "not found";
        JsonObject result = new JsonObject();
        result.addProperty("status", scenario.getStatus().toString());
        result.addProperty("reason", scenario.isFailed() ? errorMessage : successMessage);
        RestAssured.given().contentType("application/json").body(new Gson().toJson(result)).put(API_URL + "/app-automate/sessions/" + id + ".json").then().assertThat().statusCode(200);
    }

    public static String uploadApp(String appPath, String customId) {
        System.out.println("App is uploading from => " + appPath);
        try {
            Response response = RestAssured.given().contentType("multipart/form-data")
                    .multiPart("file", new File(appPath))
                    .formParam("custom_id", customId).post(API_URL + "/app-automate/upload");
            return response.statusCode() == 200 ? new Gson().fromJson(response.asString(), JsonObject.class).get("app_url").getAsString() : null;
        } catch (Exception ignored) {
            return null;
        }
    }

    public static void deleteApp(String appUrl) {
        try {
            RestAssured.given().delete(API_URL + "/app-automate/app/delete/" + appUrl).prettyPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getRecentApp() {
        try {
            RestAssured.given().contentType("application/json").get(API_URL + "/app-automate/recent_apps/").prettyPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String getRecentApp(String customId) {
        try {
            Response response = RestAssured.given().contentType("application/json").get(API_URL + "/app-automate/recent_apps/" + customId);
            return response.statusCode() == 200 ? new Gson().fromJson(response.asString(), JsonArray.class).getAsJsonArray().get(0).getAsJsonObject().get("app_url").getAsString() : null;
        } catch (Exception ignored) {
            return null;
        }
    }
}
