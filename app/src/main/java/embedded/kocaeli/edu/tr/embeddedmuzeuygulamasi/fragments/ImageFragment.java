package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.R;

/**
 * Created by oktay on 06.07.2015.
 */
public class ImageFragment extends Fragment implements ViewSwitcher.ViewFactory{

    ImageSwitcher switcher;

    Integer[] imageList = { R.drawable.h1, R.drawable.h2,
            R.drawable.h3 };

    int curIndex = 0;
    int downX, upX;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_fragment,container,false);

        switcher = (ImageSwitcher) view.findViewById(R.id.i_sw);
        switcher.setFactory(this);

        switcher.setInAnimation(AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.fade_in));
        switcher.setOutAnimation(AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.fade_out));

        switcher.setImageResource(imageList[curIndex]);
        switcher.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = (int) event.getX();
                    Log.i("event.getX()", " downX " + downX);
                    return true;
                }

                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    upX = (int) event.getX();
                    Log.i("event.getX()", " upX " + downX);
                    if (upX - downX > 100) {

                        // curIndex current image index in array viewed by user
                        curIndex--;
                        if (curIndex < 0) {
                            curIndex = imageList.length - 1;
                        }

                        switcher.setInAnimation(AnimationUtils
                                .loadAnimation(getActivity(),
                                        android.R.anim.fade_in));
                        switcher.setOutAnimation(AnimationUtils
                                .loadAnimation(getActivity(),
                                        android.R.anim.fade_out));

                        switcher.setImageResource(imageList[curIndex]);
                        // GalleryActivity.this.setTitle(curIndex);
                    }

                    else if (downX - upX > -100) {

                        curIndex++;
                        if (curIndex > imageList.length-1) {
                            curIndex = 0;
                        }

                        switcher.setInAnimation(AnimationUtils
                                .loadAnimation(getActivity(),
                                        android.R.anim.fade_in));
                        switcher.setOutAnimation(AnimationUtils
                                .loadAnimation(getActivity(),
                                        android.R.anim.fade_out));

                        switcher.setImageResource(imageList[curIndex]);
                        // GalleryActivity.this.setTitle(curIndex);
                    }
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    /**
     * Creates a new {@link View} to be added in a
     * {@link ViewSwitcher}.
     *
     * @return a {@link View}
     */
    @Override
    public View makeView() {
        ImageView i = new ImageView(getActivity());
        i.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return i;

    }
}
