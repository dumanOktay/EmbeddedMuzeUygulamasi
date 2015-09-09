package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by oktay on 09.09.2015.
 */
public class LinkButton extends Button {
    private static final String KEY_LINK = "url";
    private static final String KEY_TEXT = "title";
    private String text;
    private String link;
    
    public LinkButton(Context context,JSONObject o) {
        super(context);
        try {
            text = o.getString(KEY_TEXT);
            link = o.getString(KEY_LINK);
            this.setText(""+text);
            this.setOnClickListener(new OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                                            Constant.getActivity().startActivity(browserIntent);
                                        }
                                    }
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
