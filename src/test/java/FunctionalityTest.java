import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class FunctionalityTest {
    private static Response response;
//------------------------------------------/live-----------------------------------------------------------------------
    @Test
    public void liveInformationResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());
        response.then().body(not(hasKey("terms")));
        response.then().body(not(hasKey("privacy")));
    }
    @Test
    public void liveWithOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE).queryParam("currencies", Constants.CURRENCIES)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDNIS", nullValue());
        response.then().body("quotes.USDRUB", notNullValue());
        response.then().body(not(hasKey("terms")));
        response.then().body(not(hasKey("privacy")));
    }
    @Test
    public void liveWithAnotherSourceResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE_OTHER).queryParam("currencies", Constants.CURRENCIES)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("AUD"));
        response.then().body("quotes.AUDCAD", notNullValue());
        response.then().body("quotes.AUDEUR", notNullValue());
        response.then().body("quotes.AUDNIS", nullValue());
        response.then().body("quotes.AUDRUB", notNullValue());
        response.then().body(not(hasKey("terms")));
        response.then().body(not(hasKey("privacy")));
    }

//----------------------------------------------/historical-------------------------------------------------------------
    @Test
    public void historicalInformationResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("date", notNullValue());
        response.then().body("date", equalTo(Constants.DATE));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());
        response.then().body(not(hasKey("terms")));
        response.then().body(not(hasKey("privacy")));
    }
    @Test
    public void historicalWithOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).queryParam("source", Constants.SOURCE)
                .queryParam("currencies", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("date", notNullValue());
        response.then().body("date", equalTo(Constants.DATE));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDNIS", nullValue());
        response.then().body("quotes.USDRUB", notNullValue());
        response.then().body(not(hasKey("terms")));
        response.then().body(not(hasKey("privacy")));
    }
}
