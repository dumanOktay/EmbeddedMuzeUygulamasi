package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.provider.Settings.Secure;
import android.view.Display;
import android.view.WindowManager;

import embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit.Kvideo;


public class Constant {

	private static int screenWidth ;
	private static int screenHeight ;
	private static String videoPath;
	private static Activity activity;
	private static String ANDROID_ID;
	private static Kvideo currentKvideo;
	private static final String BASE_URL = "tanerguven.org/";
	private static final String PRE_HTTP = "http://";
	private static final String URL = "http://lab.tanerguven.org/museum-project/visitor/";
	public Constant(Context context) {
		// TODO Auto-generated constructor stub
		
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenWidth = size.x;
		screenHeight = size.y;
		
		ANDROID_ID = Secure.getString(getActivity().getContentResolver(), Secure.ANDROID_ID);
	}

	public static String getBaseUrl() {
		return BASE_URL;
	}

	public static String getPreHttp() {
		return PRE_HTTP;
	}

	public static String getURL() {
		return URL;
	}

	public static String getANDROID_ID() {
		return ANDROID_ID;
	}
	
	public static Activity getActivity() {
		return activity;
	}
	
	public static void setActivity(Activity activity) {
		Constant.activity = activity;
	}
	
	
	public static String getVideoPath() {
		return videoPath;
	}
	
	public static void setVideoPath(String videoPath) {
		Constant.videoPath = videoPath;
	}
	
	public static int getScreenWidth() {
		return screenWidth;
	}
	public static void setScreenWidth(int screenWidth) {
		Constant.screenWidth = screenWidth;
	}
	public static int getScreenHeight() {
		return screenHeight;
	}
	public static void setScreenHeight(int screenHeight) {
		Constant.screenHeight = screenHeight;
	}
	
	
	public static void setCurrentKvideo(Kvideo currentKvideo) {
		Constant.currentKvideo = currentKvideo;
	}
	
	public static Kvideo getCurrentKvideo() {
		return currentKvideo;
	}
	
}
