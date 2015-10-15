package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oktay on 14.10.2015.
 */
public class MuseumData
{

    private static final String KEY_NAME = "muzeISim";
    private static final String KEY_RELIC = "eserler";
    String name;
    String pictureURL;
    private List<Relic> relicList;


    public MuseumData(JSONObject obj){
        try{
            this.name = obj.getString(KEY_NAME);
            JSONArray array = obj.getJSONArray(KEY_RELIC);

            relicList = new ArrayList<>();
            for (int i = 0; i <array.length() ; i++) {
                final boolean add = relicList.add(new Relic(array.getJSONObject(i)));
            }
        }catch (Exception e){

        }
    }

    public List<Relic> getRelicList() {
        return relicList;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
