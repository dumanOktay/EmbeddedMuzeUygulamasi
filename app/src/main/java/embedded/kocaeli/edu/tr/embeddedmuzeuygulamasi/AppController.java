package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by oktay on 20.03.2016.
 */
public class AppController extends Application {

    private static RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        if (requestQueue == null){
            requestQueue = Volley.newRequestQueue(this);
        }
    }


    public static  void addRequest(Request request){
        getRequestQueue().add(request);
    }
    public static RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
