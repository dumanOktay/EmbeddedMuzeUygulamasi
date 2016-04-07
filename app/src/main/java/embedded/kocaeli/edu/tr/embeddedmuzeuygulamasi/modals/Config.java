package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.modals;

import android.content.Context;

/**
 * Created by oktay on 07.04.2016.
 */
public class Config {
    private static String museumId;

    private static Context context;

    public static Context getContext() {
        return context;
    }


    public static void setContext(Context context) {
        Config.context = context;
    }

    public static String getMuseumId() {
        return museumId;
    }

    public static void setMuseumId(String museumId) {
        Config.museumId = museumId;
    }
}
