package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
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
                    v.loadData("Linux (pronounced Listeni/ˈlɪnəks/ lin-əks[4][5] or, less frequently, /ˈlaɪnəks/ lyn-əks[5][6]) is a Unix-like and mostly POSIX-compliant[7] computer operating system (OS) assembled under the model of free and open-source software development and distribution. The defining component of Linux is the Linux kernel,[8] an operating system kernel first released on 5 October 1991 by Linus Torvalds.[9][10] The Free Software Foundation uses the name GNU/Linux to describe the operating system, which has led to some controversy.[11][12]\n" +
                            "\n" +
                            "Linux was originally developed as a free operating system for personal computers based on the Intel x86 architecture, but has since been ported to more computer hardware platforms than any other operating system.[13] Thanks to its dominance on smartphones, Android, which is built on top of the Linux kernel, has the largest installed base of all general-purpose operating systems.[14] Linux, in its original form, is also the leading operating system on servers and other big iron systems such as mainframe computers and supercomputers,[15][16] but is used on only around 1.5% of desktop computers.[17] Linux also runs on embedded systems, which are devices whose operating system is typically built into the firmware and is highly tailored to the system; this includes mobile phones,[18] tablet computers, network routers, facility automation controls, televisions,[19][20] video game consoles and smartwatches.[21]\n" +
                            "\n" +
                            "The development of Linux is one of the most prominent examples of free and open-source software collaboration. The underlying source code may be used, modified, and distributed—commercially or non-commercially—by anyone under licenses such as the GNU General Public License. Typically, Linux is packaged in a form known as a Linux distribution, for both desktop and server use. Some of the popular mainstream Linux distributions are Debian, Ubuntu, Linux Mint, Fedora, openSUSE, Arch Linux and Gentoo, together with commercial Red Hat Enterprise Linux and SUSE Linux Enterprise Server distributions. Linux distributions include the Linux kernel, supporting utilities and libraries, and usually a large amount of application software to fulfill the distribution's intended use.\n" +
                            "\n" +
                            "Distributions oriented toward desktop use typically include X11, a Wayland implementation or Mir as the windowing system, and an accompanying desktop environment such as GNOME or the KDE Software Compilation; some distributions may also include a less resource-intensive desktop such as LXDE or Xfce. Distributions intended to run on servers may omit all graphical environments from the standard install, and instead include other software to set up and operate a solution stack such as LAMP. Because Linux is freely redistributable, anyone may create a distribution for any intended use." + s, "text/html", "UTF-8");

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

