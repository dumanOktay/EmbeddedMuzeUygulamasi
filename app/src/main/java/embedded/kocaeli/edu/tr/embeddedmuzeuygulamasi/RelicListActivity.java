package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class RelicListActivity extends MuseumGeneralActivity {

    protected Relic selectedRelic;
    protected LinearLayout lay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);
        lay = (LinearLayout) findViewById(R.id.main);
        getRelicListview();
    }

    protected void getRelicListview() {

        lay.removeAllViews();
        ListView listView = new ListView(this);
        listView.setDividerHeight(20);
        listView.setBackgroundColor(Color.parseColor("#AAD793"));

        ArrayAdapter<Relic> arrayAdapter = new ArrayAdapter<>(this,R.layout.mytextview,
                MuseumActivity.getSelectedMuseumData().getRelicList());
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

}
