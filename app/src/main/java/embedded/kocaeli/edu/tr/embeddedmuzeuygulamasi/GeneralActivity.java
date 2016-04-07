package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.adapters.MuseumAdapter;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.ListItem;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Museum;

public class GeneralActivity extends Activity {

    private static final String TAG = "GeneralActivity";

    private static final String KEY_LIST = "list";
    private LinearLayout lay;
    private static Museum selectedMuseum;
    private static MuseumData selectedMuseumData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        lay = (LinearLayout) findViewById(R.id.main);
        Constant.setActivity(this);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://all-museums.tanerguven.org/museum_list",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            JSONObject object = new JSONObject();
                            object.put("list", array);
                            parsJson(object);

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

        AppController.addRequest(stringRequest);


    }

    public void parsJson(JSONObject object) throws JSONException {

        final List<Museum> cityDataList = new ArrayList<>();
        JSONArray array = object.getJSONArray(KEY_LIST);

        for (int i = 0; i < array.length(); i++) {
            JSONObject o = array.getJSONObject(i);

            Log.d(TAG, "parsJson: " + o);
            cityDataList.add(new Museum(o));

        }

        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));

        List<ListItem> listItems = new ArrayList<>();

        for (int i = 0; i <cityDataList.size() ; i++) {

            listItems.add(cityDataList.get(i));

        }

        MuseumAdapter arrayAdapter = new MuseumAdapter(GeneralActivity.this,listItems);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMuseum = cityDataList.get(i);
                startActivity(new Intent(GeneralActivity.this, MuseumActivity.class));

            }
        });
        lay.addView(listView);

    }


    private void getRelicListView() {
        lay.removeAllViews();
        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));

        ArrayAdapter<Relic> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, selectedMuseumData.getRelicList());
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // selectedCityData = cityDataList.get(i);
                //selectedMuseumData = selectedCityData.getMuseumDataList().get(i);

            }
        });
        lay.addView(listView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //fullScreencall();
    }

    public void fullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    public static Museum getSelectedMuseum() {
        return selectedMuseum;
    }
}

