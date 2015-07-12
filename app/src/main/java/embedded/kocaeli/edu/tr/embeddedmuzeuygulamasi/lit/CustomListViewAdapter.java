package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.R;

public class CustomListViewAdapter extends ArrayAdapter<Kvideo> {

    Context context;

    public CustomListViewAdapter(Context context, int resourceId,
                                 List<Kvideo> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDuration;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Kvideo rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.title);
            holder.txtDuration = (TextView) convertView.findViewById(R.id.textView_time);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText("");

        if (!rowItem.getIsim().isEmpty()) {

            holder.txtTitle.setText("" + rowItem.getIsim());

            int m = rowItem.getDuration()/60;
            int s = rowItem.getDuration()%60;

           if(s<=9){
               holder.txtDuration.setText(""+m+":0"+s );
           }else {
               holder.txtDuration.setText(" "+m+":"+s );
           }
        } else {
          //  new YutupString(holder.txtTitle).execute(rowItem);
        }
        holder.imageView.setImageResource(R.drawable.loading_thumbnail);
        if (rowItem.getBitmap() != null) {
            holder.imageView.setImageBitmap(rowItem.getBitmap());
        } else {
            new ImageDownloaderTask(holder.imageView, rowItem).execute("http://img.youtube.com/vi/"
                    + rowItem.getVideoid() + "/default.jpg");
        }

        return convertView;
    }
}
