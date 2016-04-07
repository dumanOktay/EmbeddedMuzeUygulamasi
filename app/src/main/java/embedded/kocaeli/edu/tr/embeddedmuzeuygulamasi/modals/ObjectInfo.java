package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.LinkButton;

/**
 * Created by oktay on 07.04.2016.
 */
public class ObjectInfo {
    private String object_name;
    private List<String> pictures;
    private String information_text;
    private String information_html;
    private List<LinkButton> urls;

    public ObjectInfo(JSONObject object) throws JSONException {
        this.object_name = object.getString("object_name");
        this.information_text = object.getString("information_text");
        this.information_html = object.getString("information_html");
        JSONArray picArray = object.getJSONArray("pictures");

        pictures = new ArrayList<>();
        urls = new ArrayList<>();
        for (int i = 0; i <picArray.length() ; i++) {
            pictures.add(picArray.getString(i));
        }

        JSONArray urlArray = object.getJSONArray("urls");
        for (int i = 0; i <urlArray.length() ; i++) {

            urls.add(new LinkButton(Config.getContext(),urlArray.getJSONObject(i)));
        }
    }


    public List<String> getPictures() {
        return pictures;
    }

    public List<LinkButton> getUrls() {
        return urls;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getInformation_text() {
        return information_text;
    }

    public void setInformation_text(String information_text) {
        this.information_text = information_text;
    }

    public String getInformation_html() {
        return information_html;
    }

    public void setInformation_html(String information_html) {
        this.information_html = information_html;
    }
}
