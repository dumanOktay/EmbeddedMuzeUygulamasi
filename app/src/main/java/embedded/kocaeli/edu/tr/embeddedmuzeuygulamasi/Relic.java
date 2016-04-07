package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktay on 15.10.2015.
 */
public class Relic {
    private static final String KEY_ID = "object_id";
    private static final String KEY_NAME = "object_name";
    private String name;
    private String id;

    public Relic(JSONObject object) throws JSONException {
        this.name = object.getString(KEY_NAME);

        this.id = object.getString(KEY_ID);
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return this.name;
    }


    public String getName() {
        return name;
    }

}
