package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MuseumActivity extends MuseumGeneralActivity {

    protected LinearLayout lay;
    protected  static MuseumData selectedMuseumData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        lay = (LinearLayout) findViewById(R.id.main);
        getMuseumListview();
    }

    protected   void getMuseumListview(){
        lay.removeAllViews();
        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));

//        ArrayAdapter<MuseumData> arrayAdapter = new ArrayAdapter<>(this,R.layout.mytextview,
//                GeneralActivity.getSelectedCityData().getMuseumDataList());
//        listView.setAdapter(arrayAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                // selectedCityData = cityDataList.get(i);
//                selectedMuseumData =  GeneralActivity.getSelectedCityData().getMuseumDataList().get(i);
//                //getRelicListView();
//                museumPresentation();
//
//            }
//        });
        lay.addView(listView);


    }

    public static MuseumData getSelectedMuseumData() {
        return selectedMuseumData;
    }

    @Override
    public void eserler(View v) {
      startActivity(new Intent(this,RelicListActivity.class));
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
