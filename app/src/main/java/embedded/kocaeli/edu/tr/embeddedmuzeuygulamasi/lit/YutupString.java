package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.os.AsyncTask;
import android.widget.TextView;


import org.json.JSONObject;

import java.lang.ref.WeakReference;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.NetWork;

public class YutupString extends AsyncTask<Kvideo, Integer, String> {
	WeakReference<TextView> tv;
	private Kvideo kvideo;
	public YutupString(TextView tv) {
		super();
		this.tv = new WeakReference<TextView>(tv);
	}

	@Override
	protected String doInBackground(Kvideo... params) {
		// TODO Auto-generated method stub
		kvideo = params[0];
		return NetWork.getDataFrOmUrL("https://www.youtube.com/oembed?url=http://www.youtube.com/watch?v="
				+ params[0].getVideoid() + "&format=json");
		
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		TextView textView = tv.get();
		try {
			JSONObject object = new JSONObject(result);
			String title = object.getString("title");
			textView.setText("" + title);
			kvideo.setIsim(title);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
