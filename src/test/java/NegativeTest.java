import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class NegativeTest {
    private static Response response;

    //------------------------------------------/live-----------------------------------------------------------------------
    @Test
    public void liveWithMistakeInNameResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.INVALID_LIFE_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(404);
        response.then().body("message", containsString("no"));
    }
    @Test
    public void liveWithBlankOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE_BLANK).queryParam("currencies", Constants.CURRENCIES_BLANK)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());
    }
    @Test
    public void liveWithMissingOptionalParamsResponseTest() {
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
    }
    @Test
    public void liveWithOptionalParamsWrongNamesResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("VOLVO", Constants.VOLVO_INSTEADofSOURCE).queryParam("BMW", Constants.BMW_INSTEADofCURRENCIES)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("message", nullValue());
        response.then().body("VOLVO", nullValue());
        response.then().body("BMW", nullValue());
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(true));
        response.then().body("timestamp", notNullValue());
        response.then().body("source", notNullValue());
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());
    }
    @Test
    public void liveWithSourceWrongValuesResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE_WRONG_VALUE).queryParam("currencies", Constants.CURRENCIES)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(201));
        response.then().body("error.info", notNullValue());
        response.then().body("error.info", containsString("invalid Source"));
    }
    @Test
    public void liveWithCurrenciesWrongValuesResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE).queryParam("currencies", Constants.CURRENCIES_WRONG_VALUES)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(202));
        response.then().body("error.info", notNullValue());
        response.then().body("error.info", containsString("invalid Currency"));
    }
    @Test
    public void liveWithNISWrongCurrencyCodeResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE).queryParam("currencies", Constants.NIS_WRONG_CODE)
                .get(Constants.URL + Constants.LIVE_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(202));
        response.then().body("error.info", notNullValue());
        response.then().body("error.info", containsString("invalid Currency"));
    }

    //------------------------------------------/historical-----------------------------------------------------------------
    @Test
    public void historicalWithMistakeInNameResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).get(Constants.URL + Constants.INVALID_HISTERICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(404);
        response.then().body("message", containsString("no"));
    }
    @Test
    public void historicalWitMissingDateResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("source", Constants.SOURCE).queryParam("currencies", Constants.CURRENCIES)
                .get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("a date"));
    }
    @Test
    public void historicalWithMissingOptionalParamsResponseTest() {
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
    }
    @Test
    public void historicalWithMissingDateAndOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("a date"));
    }
    @Test
    public void historicalWithBlankOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).queryParam("source", Constants.SOURCE_BLANK)
                .queryParam("currencies", Constants.CURRENCIES_BLANK).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
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
    }
    @Test
    public void historicalWithBlankDateResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE_BLANK).queryParam("source", Constants.SOURCE)
                .queryParam("currencies", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("a date"));
    }
        @Test
    public void historicalWithBlankDateAndOptionalParamsResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE_BLANK).queryParam("source", Constants.SOURCE_BLANK)
                .queryParam("currencies", Constants.CURRENCIES_BLANK).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("a date"));
    }
    @Test
    public void historicalWithInvalidDateResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE_INVALID).queryParam("source", Constants.SOURCE)
                .queryParam("currencies", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(302));
        response.then().body("error.info", containsString("invalid date"));
    }
    @Test
    public void historicalWithDateWrongNameResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("data", Constants.DATE).queryParam("source", Constants.SOURCE)
                .queryParam("currencies", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("a date"));
    }
    @Test
    public void historicalWithOptionalParamsWrongNamesResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).queryParam("score", Constants.SOURCE)
                .queryParam("currier", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
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
    }
    @Test
    public void historicalWithWrongSourceValueResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).queryParam("source", Constants.SOURCE_WRONG_VALUE)
                .queryParam("currencies", Constants.CURRENCIES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(201));
        response.then().body("error.info", containsString("invalid Source"));
    }
    @Test
    public void historicalWithWrongCurrenciesValueResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE).queryParam("source", Constants.SOURCE)
                .queryParam("currencies", Constants.CURRENCIES_WRONG_VALUES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", notNullValue());
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(202));
        response.then().body("error.info", containsString("one or more"));
    }
    @Test
    public void historicalWithWrongDateAndOptionalParamsValueResponseTest() {
        response = given().header("apikey", Constants.APIKEY).contentType("application/json")
                .queryParam("date", Constants.DATE_INVALID).queryParam("source", Constants.SOURCE_WRONG_VALUE)
                .queryParam("currencies", Constants.CURRENCIES_WRONG_VALUES).get(Constants.URL + Constants.HISTORICAL_RATES_ENDPOINT);
        System.out.println(response.asString());

        response.then().statusCode(200);
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(201));
        response.then().body("error.info", containsString("invalid Source"));
    }
}
