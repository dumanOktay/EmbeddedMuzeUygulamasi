package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktay on 14.10.2015.
 */
public class CityData {

    private static final String KEY_ARRAY = "muzeList";
    private static final String KEY_NAME = "sehirIsim";
    String name;
    List<MuseumData> museumDataList;

    public CityData(JSONObject o) throws JSONException{
        this.name =o.getString(KEY_NAME);
        JSONArray museumArray = o.getJSONArray(KEY_ARRAY);

        museumDataList = new ArrayList<>();

        for (int i = 0; i <museumArray.length() ; i++) {
            JSONObject obj = museumArray.getJSONObject(i);
            museumDataList.add(new MuseumData(obj));
        }
    }

    public List<MuseumData> getMuseumDataList() {
        return museumDataList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ""+this.name;
    }
}
