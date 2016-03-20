package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.os.AsyncTask;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.lang.ref.WeakReference;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.AppController;


public class YutupString extends AsyncTask<Kvideo, Integer, String> {
	WeakReference<TextView> tv;
	private Kvideo kvideo;
	String tempString;
	public YutupString(TextView tv) {
		super();
		this.tv = new WeakReference<TextView>(tv);


	}

	@Override
	protected String doInBackground(Kvideo... params) {
		// TODO Auto-generated method stub
		kvideo = params[0];





		StringRequest stringRequest = new StringRequest(Request.Method.GET, "",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {

						tempString = response;
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {

						tempString = null;
					}
				}
		);

		AppController.getRequestQueue().add(stringRequest);
		return "";
		
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
