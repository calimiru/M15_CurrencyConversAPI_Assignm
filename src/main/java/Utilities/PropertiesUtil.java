package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    static Properties properties = new Properties();
    public void loadProperties(){
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperties(String APIKEY){
        String header = System.getenv(APIKEY);
        if(header ==null || header.isEmpty()){
            if(properties.isEmpty()){
                new PropertiesUtil().loadProperties();
            }
            return getProperties(APIKEY);
        }
        else return header;
    }
}
