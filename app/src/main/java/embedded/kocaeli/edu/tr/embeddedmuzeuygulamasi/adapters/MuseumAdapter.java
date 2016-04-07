package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.GeneralActivity;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.R;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.ListItem;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals.Museum;

/**
 * Created by oktay on 07.04.2016.
 */
public class MuseumAdapter extends BaseAdapter {
    private Context context;
    private List<ListItem> listItems;

    private static final String TAG = "MuseumAdapter";
    
    public MuseumAdapter(Context context, List<ListItem> listItems) {
        this.context = context;
        this.listItems = listItems;
    }


    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyView myHolder;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.item_view, parent, false);
            myHolder = new MyView(convertView);
            convertView.setTag(myHolder);

        } else {
            myHolder = (MyView) convertView.getTag();
        }


        ListItem listItem = (ListItem) getItem(position);
        myHolder.textView.setText("" + listItem.getName());
        Log.d(TAG, "getView: "+ listItem.getName());
        return convertView;
    }

    private class MyView {
        TextView textView;

        public MyView(View item) {
            textView = (TextView) item.findViewById(R.id.item_tv);
        }
    }
}
