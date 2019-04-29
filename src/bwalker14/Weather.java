package bwalker14;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class Weather {
    private String clientID = "wQhXMMnxoRV4HNKoRLZrL";
    private String clientSecret = "rUOW0GEyf5bT9JhUzro2WQAuUpj3A7nFHgVCRGEK";


    private String zip;
    private JsonElement results;


    public Weather(String i)
    {
        try
        {
            zip = URLEncoder.encode(i, "utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public void fetch()
    {
        String urlString = "https://api.aerisapi.com/observations/"
                + zip + "?"
                + "client_id=" + clientID
                + "&client_secret=" + clientSecret;
        try
        {
            // Make URL object from urlString
            URL url = new URL(urlString);

            // Open stream readers for incoming data
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            // Parse into JSON
            JsonParser parser = new JsonParser();
            results = parser.parse(br);
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getCityState()
    {
       JsonObject place = results.getAsJsonObject()
               .get("response").getAsJsonObject()
               .get("place").getAsJsonObject(); //Gets the Json Object containing place information

       String city = place.get("name").getAsString();
       city = city.substring( 0, 1 ).toUpperCase() + city.substring( 1 );
       String state = place.get("state").getAsString();
       state = state.toUpperCase(); //Capitalizes the state initials
       return city + ", " + state;

    }

    public String getTemperature()
    {
        JsonObject ob = results.getAsJsonObject()
                .get("response").getAsJsonObject()
                .get("ob").getAsJsonObject(); //Gets the Json Object temperature information
        return ob.get("tempF").getAsString() + "°F";

    }

    public String getWeather()
    {
        JsonObject ob = results.getAsJsonObject()
                .get("response").getAsJsonObject()
                .get("ob").getAsJsonObject(); //Gets the Json Object temperature information
        return ob.get("weather").getAsString();

    }

}
