package com.example.xmljson_091;
//Mohit Gupta 1CR18IS091
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;import android.view.View;
import android.widget.Button;import android.widget.TextView;
import org.json.JSONObject;import org.w3c.dom.Document;
import org.w3c.dom.Element;import org.w3c.dom.NodeList;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
public class MainActivity extends AppCompatActivity
{
    TextView xmlText, jsonText;
    Button xmlButton, jsonButton;
    String KEY_NAME = "name";
    String KEY_LATITUDE = "latitude";
    String KEY_LONGITUDE = "longitude";
    String KEY_TEMPERATURE = "temperature";
    String KEY_HUMIDITY = "humidity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xmlText = findViewById(R.id.xmlHead);
        jsonText = findViewById(R.id.jsonHead);
        // Hide the Table Names
        xmlText.setVisibility(View.GONE);
        jsonText.setVisibility(View.GONE);
        xmlButton = findViewById(R.id.buttonXML);
        jsonButton = findViewById(R.id.buttonJSON);
        xmlButton.setOnClickListener(this::parseXML);
        jsonButton.setOnClickListener(this::parseJSON);
    }
    private String getValue(Element element, String str) {
        NodeList n = element.getElementsByTagName(str);
        return n.item(0).getFirstChild().getNodeValue();
    }
    private void parseXML(View v)
    {
        xmlText.setVisibility(View.VISIBLE);
        try {
            InputStream is = getAssets().open("city.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = factory.newDocumentBuilder();
            Document dom = db.parse(is);
            NodeList city = dom.getElementsByTagName("city");
            String name = "";
            String lat = "";
            String lon = "";
            String temp = "";
            String humid = "";
            for (int i = 0; i < city.getLength(); i++) {
                Element e = (Element) city.item(i);
                name = name.equals("") ? this.getValue(e, KEY_NAME) : name;
                lat = lat.equals("") ? this.getValue(e, KEY_LATITUDE) : lat;
                lon = lon.equals("") ? this.getValue(e, KEY_LONGITUDE) : lon;
                temp = temp.equals("") ? this.getValue(e, KEY_TEMPERATURE) : temp;
                humid = humid.equals("") ? this.getValue(e, KEY_HUMIDITY) : humid;
            }
            ((TextView) findViewById(R.id.xmlName)).setText("Name: " + name);
            ((TextView) findViewById(R.id.xmlLat)).setText("Latitude: " + lat);
            ((TextView) findViewById(R.id.xmlLong)).setText("Longitude: " + lon);
            ((TextView) findViewById(R.id.xmlTemp)).setText("Temperature: " + temp);
            ((TextView) findViewById(R.id.xmlHumid)).setText("Humidity: " + humid);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void parseJSON(View v) {
        jsonText.setVisibility(View.VISIBLE);
        try {
            InputStream is = getAssets().open("city.json");
            int size = is.available(); // Returns number of bytes that can be read
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            JSONObject city = new JSONObject(new String(buffer, "UTF-8"));
            ((TextView) findViewById(R.id.jsonName)).setText("Name: " + city.getString(KEY_NAME));
            ((TextView) findViewById(R.id.jsonLat)).setText("Latitude: " + city.getString(KEY_LATITUDE));
            ((TextView) findViewById(R.id.jsonLong)).setText("Longitude: " + city.getString(KEY_LONGITUDE));
            ((TextView) findViewById(R.id.jsonTemp)).setText("Temperature: " + city.getString(KEY_TEMPERATURE));
            ((TextView) findViewById(R.id.jsonHumid)).setText("Humidity: " + city.getString(KEY_HUMIDITY));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}