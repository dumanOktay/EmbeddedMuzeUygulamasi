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
    private static final String KEY_DES = "aciklama";
    private static final String KEY_PIC_URL = "resim";
    private static final String KEY_NAME ="eserISim" ;
    private String name;
    private List<String> picUrl;
    private String description;


    public Relic(JSONObject object) throws JSONException {
        this.name = object.getString(KEY_NAME);
        this.picUrl =new ArrayList<>();
        JSONArray array = object.getJSONArray(KEY_PIC_URL);
        for (int i = 0; i <array.length() ; i++) {
            picUrl.add(array.getString(i));
        }
        this.description = object.getString(KEY_DES);

    }



    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.name;
    }


    public String getName() {
        return name;
    }

    public List<String> getPicUrl() {
        return picUrl;
    }
}
