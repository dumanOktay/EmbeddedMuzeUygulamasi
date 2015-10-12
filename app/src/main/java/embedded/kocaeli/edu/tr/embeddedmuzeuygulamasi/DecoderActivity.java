package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DecoderActivity extends Activity implements QRCodeReaderView.OnQRCodeReadListener {


    private TextView myTextView;
    private QRCodeReaderView mydecoderview;
    private ImageView line_image;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_museum_general);

        setContentView(R.layout.activity_decoder);

        mydecoderview = (QRCodeReaderView) findViewById(R.id.qrdecoderview);
        mydecoderview.setOnQRCodeReadListener(this);

        myTextView = (TextView) findViewById(R.id.exampleTextView);
        Constant.setActivity(this);
        line_image = (ImageView) findViewById(R.id.red_line_image);
        TranslateAnimation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f);
        mAnimation.setDuration(1000);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        line_image.setAnimation(mAnimation);
    }


    // Called when a QR is decoded
    // "text" : the text encoded in QR
    // "points" : points where QR control points are placed
    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        myTextView.setText(text);
        id = text;
        new getData().execute();
        mydecoderview.getCameraManager().stopPreview();
    }


    // Called when your device have no camera
    @Override
    public void cameraNotFound() {

    }


    // Called when there's no QR codes in the camera preview image
    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }


    class getData extends AsyncTask<Void, Integer, JSONObject> {
        @Override
        protected JSONObject doInBackground(Void... voids) {
            String s = NetWork.getDataFrOmUrL(Constant.getURL() + "presentation_info?presentation_id=" + id);

            try {
                JSONObject o = new JSONObject(s);
                return o;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);

            if (jsonObject == null) {
                Toast.makeText(DecoderActivity.this, "Hatalı Qr Code", Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONArray pictures = jsonObject.getJSONArray("pictures");
                    Toast.makeText(DecoderActivity.this, "  " + pictures.get(0), Toast.LENGTH_LONG).show();
                    setContentView(R.layout.presentation_info);
                    LinearLayout mainLayout = (LinearLayout) findViewById(R.id.ust_layout);

                    WebView webView = new WebView(DecoderActivity.this);
                    String url = pictures.get(0).toString();

                    LinearLayout layout = new LinearLayout(DecoderActivity.this);

                    LinearLayout.LayoutParams leftMarginParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    leftMarginParams.weight = 1;


                    String imgSrcHtml = "<html><img src='" + url + "'  width=\"100%\" /></html>";
                    webView.loadData(imgSrcHtml, "text/html", "UTF-8");

                    WebView v = new WebView(DecoderActivity.this);
                    String s = jsonObject.getString("information_html");
                    v.loadDataWithBaseURL("",s, "text/html", "UTF-8","");
                    layout.setLayoutParams(leftMarginParams);

                    mainLayout.addView(webView);
                    mainLayout.addView(v);

                    JSONArray array = jsonObject.getJSONArray("urls");
                    LinkButton button1 = new LinkButton(DecoderActivity.this, array.getJSONObject(0));
                    mainLayout.addView(button1);
                    LinkButton button2 = new LinkButton(DecoderActivity.this, array.getJSONObject(1));
                    mainLayout.addView(button2);
                    LinkButton button3 = new LinkButton(DecoderActivity.this, array.getJSONObject(2));
                    mainLayout.addView(button3);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

