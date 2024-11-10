import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class SecurityTest {
    private static Response response;

    //----------------------------------------/live---------------------------------------------------------------------
    @Test
    public void liveValidApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json").get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        response.then().statusCode(200);
        response.then().body("success", equalTo(true));
    }
    @Test
    public void liveInvalidApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.INVALID_APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("Invalid"));
    }
    @Test
    public void liveBlankApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.BLANK_APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("No API"));
    }
    @Test
    public void liveMissingApikeyAuthenticationTest() {
        response = given().contentType("application/json").get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("No "));
    }
    //---------------------------------/historical----------------------------------------------------------------------
    @Test
    public void historicalValidApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json").queryParam("date", Constants.DATE)
                .get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        response.then().statusCode(200);
    }
    @Test
    public void historicalInvalidApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.INVALID_APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("Invalid"));
    }
    @Test
    public void historicalBlankApikeyAuthenticationTest() {
        response = given().header("apikey", Constants.BLANK_APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("No"));
    }
    @Test
    public void historicalMissingApikeyAuthenticationTest() {
        response = given().contentType("application/json").get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        response.then().statusCode(401);
        response.then().body("message", containsString("No"));
    }
}
