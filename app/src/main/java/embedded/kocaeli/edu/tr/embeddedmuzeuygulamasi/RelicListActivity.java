package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Museum;

public class RelicListActivity extends MuseumGeneralActivity {

    private static final String TAG = "RelicListActivity";
    
    private static Museum parentMuseum;

    public static final String RELIC_METHOD_URL = "/museum/object_list";
    protected Relic selectedRelic;
    protected LinearLayout lay;

    private List<Relic> relicList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        lay = (LinearLayout) findViewById(R.id.main);
        getRelicListview();
    }

    protected void getRelicListview() {

        //TO DO     MÜZE İNFO
        String url = Constant.getPreHttp() + parentMuseum.getId() +"."+ Constant.getBaseUrl() + RELIC_METHOD_URL;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            Log.d(TAG, "onResponse() called with: " + "response = [" + response + "]");
                            JSONArray array = new JSONArray(response);


                            Log.d(TAG, "onResponse: s  "+array.length());

                            relicList.clear();
                            for (int i = 0; i <array.length() ; i++) {
                                relicList.add(new Relic(array.getJSONObject(i)));
                            }



                            lay.removeAllViews();
                            ListView listView = new ListView(RelicListActivity.this);
                            listView.setDividerHeight(20);
                            listView.setBackgroundColor(Color.parseColor("#AAD793"));

                            ArrayAdapter<Relic> arrayAdapter = new ArrayAdapter<Relic>(RelicListActivity.this,R.layout.mytextview,
                                    relicList);
                            listView.setAdapter(arrayAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    // selectedCityData = cityDataList.get(i);
                                    selectedRelic =  MuseumActivity.getSelectedMuseumData().getRelicList().get(i);
                                    relicPresentation();
                                }
                            });
                            lay.addView(listView);

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


    private void relicPresentation() {

        setContentView(R.layout.activity_museum_general);
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.toplayout_2);
        LinearLayout layout3 = (LinearLayout) findViewById(R.id.toplayout_3);
        LinearLayout layout4 = (LinearLayout) findViewById(R.id.toplayout_4);
        Button qrButton = (Button) findViewById(R.id.btn_qrkod);
        Button eserButton =(Button)findViewById(R.id.btn_eserler);
        TextView title =(TextView) findViewById(R.id.title_view);

        title.setText(""+selectedRelic.getName());
        String url = selectedRelic.getPicUrl().get(0);
        //Layouta webview ekle(Resim için )


        WebView webView = new WebView(this);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String imgSrcHtml = "<html><body style=\"margin: 0; padding: 0\">" +
                "<img src='" + url + "'   width=\"100%\" /></html>";
        webView.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout2.addView(webView);

        WebView webView2 = new WebView(this);
        webView2.getSettings().setDefaultTextEncodingName("utf-8");
        imgSrcHtml = "<html> " +
                "<p><font face=\"Courier New\" color=\"#282828\"> '"
                + selectedRelic.getDescription() + "'  " +
                " </font></p>" +
                "</html>";
        webView2.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout3.addView(webView2);

        eserButton.setText("Daha Fazla Bilgi");
        layout4.removeView(qrButton);
    }

    public static Museum getParentMuseum() {
        return parentMuseum;
    }

    public static void setParentMuseum(Museum parentMuseum) {
        RelicListActivity.parentMuseum = parentMuseum;
    }
}
