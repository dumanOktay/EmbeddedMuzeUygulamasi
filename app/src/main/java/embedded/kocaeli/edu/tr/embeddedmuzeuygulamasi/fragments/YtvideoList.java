package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.Constant;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.R;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.YoutubeActivity;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit.CustomListViewAdapter;
import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit.Kvideo;

/**
 * Created by oktay on 15.06.2015.
 */
public class YtvideoList extends Fragment {

    private static List<Kvideo> kvideos;
    private static ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ytvideolist,container,false);
        lv = (ListView) view.findViewById(R.id.listView2);
        Constant.setActivity(getActivity());
        new fetchUrl().execute("");
        return  view;
    }


    public static class fetchUrl extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... params) {
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            Toast.makeText(Constant.getActivity(),"Veri geldi",Toast.LENGTH_LONG).show();
            kvideos = new ArrayList<Kvideo>();
            if(result != null){
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray items = object.getJSONArray("items");


                    for (int i = 0; i < items.length(); i++) {
                        JSONObject o = items.getJSONObject(i);
                        JSONObject snippet= o.getJSONObject("snippet");
                        String id = snippet.getJSONObject("resourceId").getString("videoId");
                        String title = snippet.getString("title");
                        int d = 1;
                        kvideos.add(new Kvideo(id, title,d));

                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                CustomListViewAdapter adapter = new CustomListViewAdapter(Constant.getActivity(), R.layout.list_item, kvideos);
                lv.setAdapter(adapter);
                lv.setDividerHeight(20);

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        Kvideo kvideo = kvideos.get(position);
                        Constant.setCurrentKvideo(kvideo);
                        Constant.getActivity().startActivity(new Intent(Constant.getActivity(),
                                YoutubeActivity.class));
                    }
                });

            }else {
                Toast.makeText(Constant.getActivity(),"Veri null",Toast.LENGTH_LONG).show();
            }
        }
    }
}
