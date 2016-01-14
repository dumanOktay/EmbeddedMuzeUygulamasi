package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GeneralActivity extends Activity {

    private static final String KEY_LIST = "list";
    private LinearLayout lay;
    private static CityData selectedCityData;
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
        String js= FileUtils.readFile("yeni.json");

        try {
            JSONObject object = new JSONObject(js);

            parsJson(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void parsJson(JSONObject object) throws JSONException{

        final List<CityData> cityDataList = new ArrayList<>();
        JSONArray array = object.getJSONArray(KEY_LIST);

        for (int i=0;i<array.length();i++){
            JSONObject o = array.getJSONObject(i);
            cityDataList.add(new CityData(o));

        }

        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));

        ArrayAdapter<CityData> arrayAdapter = new ArrayAdapter<>(this, R.layout.mytextview, cityDataList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCityData = cityDataList.get(i);
                startActivity(new Intent(GeneralActivity.this,MuseumActivity.class));

            }
        });
        lay.addView(listView);

    }



    private void getRelicListView(){
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

    public static CityData getSelectedCityData() {
        return selectedCityData;
    }
}

