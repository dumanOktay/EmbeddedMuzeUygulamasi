package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class StartActivty extends Activity {

    private static final String TAG = "StartActivty";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start_activty);
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

    public void clickStart(View v) {
        Log.d(TAG, "clickStart() called with: " + "v = [" + v.getId() + "]");
        switch (v.getId()) {
            case R.id.txt_genel:  //tüm müzeler giriş
                startActivity(new Intent(this, GeneralActivity.class));
                break;
            case R.id.txt_gecmis:
                break;
            case R.id.txt_gezilen:
                startActivity(new Intent(this, MuseumGeneralActivity.class));
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        fullScreencall();
    }
}
