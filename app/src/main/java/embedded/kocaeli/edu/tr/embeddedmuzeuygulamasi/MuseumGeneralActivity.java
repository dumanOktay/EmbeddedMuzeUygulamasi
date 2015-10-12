package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;


/*
* Müze hakkında genel bilgilerin olduğu Activity
*
* */

public class MuseumGeneralActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_museum_general);

        //Layouta webview ekle(REsim için )
        LinearLayout layout2 = (LinearLayout) findViewById(R.id.toplayout_2);


        String url = "http://img01.imgfotokritik.com/fk_new/big/2/7/3/273626/1459405-osman-hamdi-bey8217in-yalisi.jpg";
        WebView webView = new WebView(this);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String imgSrcHtml = "<html><body style=\"margin: 0; padding: 0\">" +
                "<img src='" + url + "'   width=\"100%\" /></html>";
        webView.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout2.addView(webView);


        LinearLayout layout3 = (LinearLayout) findViewById(R.id.toplayout_3);


        String url2 = "Büyük Türk Müzecisi ve Ressamı Osman Hamdi Bey (1842-1910) tarafından 1884 yılında Gebze Eskihisar'ın batı sahiline köşk, " +
                "resimhane, kayıkhane ve müştemilat şeklinde inşa edilmiştir.";
        WebView webView2 = new WebView(this);
        webView2.getSettings().setDefaultTextEncodingName("utf-8");
        imgSrcHtml = "<html> " +
                "<p><font face=\"Courier New\" color=\"#282828\"> '"
                + url2 + "'  " +
                " </font></p>" +
                "</html>";
        webView2.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
        layout3.addView(webView2);


    }

    public void eserler(View v) {
       //eserler activitye gidilecek
    }

    public void qrCodeRead(View v){
        startActivity(new Intent(MuseumGeneralActivity.this,DecoderActivity.class));
    }
}
