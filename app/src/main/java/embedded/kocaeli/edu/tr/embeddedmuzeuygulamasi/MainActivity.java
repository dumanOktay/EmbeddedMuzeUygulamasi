package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.fragments.ImageFragment;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.fragments.MainFragment;


public class MainActivity extends FragmentActivity {

    private FragmentTransaction transaction;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.container,new MainFragment());
        ImageButton b = (ImageButton) findViewById(R.id.button1);
        LinearLayout l = (LinearLayout) b.getParent();
        l.setBackgroundColor(Color.BLUE);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void click(View v){
        transaction = manager.beginTransaction();
        switch (v.getId()){

            case R.id.button1:
                transaction.replace(R.id.container,new MainFragment());
                ImageButton b = (ImageButton) findViewById(R.id.button1);
                LinearLayout l = (LinearLayout) b.getParent();
                l.setBackgroundColor(Color.parseColor("#2d2d2d"));
                break;
            case R.id.button2:
                transaction.replace(R.id.container,new ImageFragment());
                break;
        }
        transaction.commit();
    }
}
