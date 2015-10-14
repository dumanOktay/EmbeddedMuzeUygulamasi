package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oktay on 14.10.2015.
 */
public class MuseumData
{

    private static final String KEY_NAME = "muzeISim";
    String name;
    String pictureURL;


    public MuseumData(JSONObject obj) throws JSONException{
        this.name = obj.getString(KEY_NAME);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
