package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Config;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Museum;

public class MuseumActivity extends MuseumGeneralActivity {


    Museum selectedMuseum = GeneralActivity.getSelectedMuseum();
    private static final String TAG = "MuseumActivity";
    protected static MuseumData selectedMuseumData;
    public static final String MUSEUM_METHOD_URL = "/museum/museum_information";

    private TextView headerTv;

    private Museum currentMuseum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        getMuseumListview();

        headerTv =(TextView)findViewById(R.id.header_tv);

        Log.d(TAG, "onCreate: kk");
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.ekle_btn:

                relicList();

                break;
        }
    }

    protected void getMuseumListview() {
        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));


        //TO DO     MÜZE İNFO
        String url = Constant.getPreHttp() + selectedMuseum.getId() +"."+ Constant.getBaseUrl() + MUSEUM_METHOD_URL;

        Log.d(TAG, "getMuseumListview: url  "+url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            currentMuseum = new Museum(response);
                            Config.setMuseumId(currentMuseum.getId());
                            headerTv.setText(""+currentMuseum.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        Log.d(TAG, "getMuseumListview: url   "+ url);
        AppController.getRequestQueue().add(request);

    }

    public static MuseumData getSelectedMuseumData() {
        return selectedMuseumData;
    }

    @Override
    public void eserler(View v) {

       if (currentMuseum != null){
           RelicListActivity.setParentMuseum(currentMuseum);
           startActivity(new Intent(this, RelicListActivity.class));
       }
    }



    public void relicList(){

        //TO DO     MÜZE İNFO
        String url = Constant.getPreHttp() + selectedMuseum.getId() +"."+ Constant.getBaseUrl() + MUSEUM_METHOD_URL;

        Log.d(TAG, "getMuseumListview: url  "+url);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            currentMuseum = new Museum(response);
                            headerTv.setText(""+currentMuseum.getName());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        Log.d(TAG, "getMuseumListview: url   "+ url);
        AppController.getRequestQueue().add(request);
    }

}
