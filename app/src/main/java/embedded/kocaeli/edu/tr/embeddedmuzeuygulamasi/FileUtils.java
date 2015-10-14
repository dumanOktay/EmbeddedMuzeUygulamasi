package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by oktay on 14.10.2015.
 */
public class FileUtils {

    public static String  readFile(String filename){
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(Constant.getActivity().getAssets().open(filename)));

            // do reading, usually loop until end of file reading
            String mLine = reader.readLine();
            while (mLine != null) {
                //process line
                mLine = reader.readLine();
                if (mLine != null){
                   if (!mLine.contains("null")){
                       builder.append(mLine);
                       //System.out.println("" + mLine);
                       Log.i("FileUtiils",mLine);
                   }
                }

                //builder.append("\n");
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return builder.toString();
    }
}
