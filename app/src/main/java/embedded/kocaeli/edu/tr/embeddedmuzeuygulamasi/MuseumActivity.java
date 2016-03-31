package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import org.json.JSONException;
import org.json.JSONObject;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Museum;

public class MuseumActivity extends MuseumGeneralActivity {

    private static final String TAG = "MuseumActivity";
    protected LinearLayout lay;
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
    }

    public void click(View v){
        switch (v.getId()){
            case R.id.ekle_btn:


                break;
        }
    }

    protected void getMuseumListview() {
        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));


        Museum selectedMuseum = GeneralActivity.getSelectedMuseum();


        //TO DO     MÜZE İNFO
        String url = Constant.getPreHttp() + selectedMuseum.getId() +"."+ Constant.getBaseUrl() + MUSEUM_METHOD_URL;


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

    private void museumPresentation() {

        setContentView(R.layout.activity_museum_general);
        String url = selectedMuseumData.getPictureURL();
        //Layouta webview ekle(Resim için )
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.toplayout_2);
        WebView webView = new WebView(this);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String imgSrcHtml = "<html><body style=\"margin: 0; padding: 0\">" +
                "<img src='" + url + "'   width=\"100%\" /></html>";
        webView.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout2.addView(webView);


        LinearLayout layout3 = (LinearLayout) findViewById(R.id.toplayout_3);


        WebView webView2 = new WebView(this);
        webView2.getSettings().setDefaultTextEncodingName("utf-8");
        imgSrcHtml = "<html> " +
                "<p><font face=\"Courier New\" color=\"#282828\"> '"
                + selectedMuseumData.getDescription() + "'  " +
                " </font></p>" +
                "</html>";
        webView2.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout3.addView(webView2);

        LinearLayout layout4 = (LinearLayout) findViewById(R.id.toplayout_4);
        Button qrButton = (Button) findViewById(R.id.btn_qrkod);
        layout4.removeView(qrButton);

    }

}
