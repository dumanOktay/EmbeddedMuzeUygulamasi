package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.R;

/**
 * Created by oktay on 06.07.2015.
 */
public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment,container,false);
        return  view;
    }
}
