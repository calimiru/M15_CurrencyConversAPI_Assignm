import Utilities.PropertiesUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Constants {
//    URL and API key
    public static final String URL = "https://api.apilayer.com";
    public static final String APIKEY = PropertiesUtil.getProperties("APIKEY");

//    Invalid apikey data
    public static final String INVALID_APIKEY = "WBEpsNwuPcdrRQdFI2JAA5EsuqJgA23";
    public static final String BLANK_APIKEY = " ";

//    Endpoints
    public static final String LIVE_RATES_ENDPOINT = "/currency_data/live";
    public static final String HISTORICAL_RATES_ENDPOINT = "/currency_data/historical";

//    Invalid endpoints data
    public static final String INVALID_LIFE_ENDPOINT = "/currency_data/life";
    public static final String INVALID_HISTERICAL_RATES_ENDPOINT = "/currency_data/histerical";

//  Dates
    public static final String DATE = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    public static final String DATE_BLANK = "";
    public static final String DATE_INVALID = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

//    Currencies and sources
    public static final String CURRENCIES = "CAD,EUR,NIS,RUB";
    public static final String CURRENCIES_BLANK = "";
    public static final String BMW_INSTEADofCURRENCIES = "CAD";
    public static final String CURRENCIES_WRONG_VALUES = "CND,EURO,NSI,RUR";
    public static final String NIS_WRONG_CODE = "NIS";
    public static final String SOURCE = "USD";
    public static final String SOURCE_BLANK = "";
    public static final String SOURCE_OTHER = "AUD";
    public static final String VOLVO_INSTEADofSOURCE = "RUB";
    public static final String SOURCE_WRONG_VALUE = "UDS";
}
