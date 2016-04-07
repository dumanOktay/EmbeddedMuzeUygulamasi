package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;

/**
 * Created by oktay on 07.04.2016.
 */
public class CustomWeb {

    private String url;
    private Context context;
    private WebView webView;

    public CustomWeb(String url, Context context) {
        this.url = url;
        this.context = context;
        webView = new WebView(this.context);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String imgSrcHtml = "<html><body style=\"margin: 0; padding: 0\">" +
                "<img src='" + url + "'   width=\"100%\" /></html>";
        webView.loadDataWithBaseURL("", imgSrcHtml, "text/html", "utf-8", "");
    }


    public CustomWeb(int a, String url, Context context) {
        this.url = url;
        this.context = context;
        webView = new WebView(this.context);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        webView.loadDataWithBaseURL("", url, "text/html", "utf-8", "");
    }

    public WebView getWebView() {
        return webView;
    }
}
