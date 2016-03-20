package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oktay on 20.03.2016.
 */
public class Museum {
    private String id;
    private String name;

    private static final String KEY_ID = "museum_id";
    private static final String KEY_NAME = "museum_name";



    public Museum(JSONObject  o) throws JSONException {
        this.id = o.getString(KEY_ID);
        this.name= o.getString(KEY_NAME);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ""+getName();
    }
}
