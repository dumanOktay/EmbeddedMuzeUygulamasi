package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class StaticTools {
	
	private static Context context;
	private static int position;
	private static SharedPreferences preferences;
	private static Editor editor;
	private static boolean showDialog;
	
	private static boolean showName;
	
	private static String[] urunDizi;
	private static String KEY_POS = "position";
	private static String KEY_BEKLEME = "bekleme";
	private static String KEY_KULLANICI = "kullanici";
	private static String KEY_SAYAC = "sayac02";
	private static String araString;

	
	
	
	public StaticTools(Context context) {
		// TODO Auto-generated constructor stub
		StaticTools.context = context;
		
		preferences = context.getSharedPreferences("bekleme", Context.MODE_PRIVATE);
		editor = preferences.edit();
			
	}
	
	public static void guncelle() {
		
		}
		
	
	public static int getBekleme() {
		return preferences.getInt(KEY_BEKLEME, 30);
	}
	
	public static void setKullanici(String isim) {
		editor.putString(KEY_KULLANICI, isim);
		editor.commit();
	}
	
	public static String getKullaniciAdi() {
		return preferences.getString(KEY_KULLANICI, "null");
	}
	
	public static String[] getUrunDizi() {
		return urunDizi;
	}
	
	public static void setUrunDizi(String[] urunDizi) {
		StaticTools.urunDizi = urunDizi;
	}
	
	
	public static String getAraString() {
		return araString;
	}
	
	public static void setAraString(String araString) {
		StaticTools.araString = araString;
	}

	public static Context getContext() {
		return context;
	}


	public static void setContext(Context context) {
		StaticTools.context = context;
	}


	public static SharedPreferences getPreferences() {
		return preferences;
	}


	public static void setPreferences(SharedPreferences preferences) {
		StaticTools.preferences = preferences;
	}


	public static Editor getEditor() {
		return editor;
	}
	
	public static int getPosition() {
		return position;
	}
	
	public static void setPosition(int position) {
		StaticTools.position = position;
	}
	
	public static int getSavedPosition() {
		return preferences.getInt(KEY_POS, 0);
	}
	public static void savePosition(int position) {
		System.out.println("kaydedildi "+position);
		
		if(position ==5){
			position =0;
		}
		StaticTools.position = position;
		editor.putInt(KEY_POS, position);
		editor.commit();
	}
	
	public static int getSayac() {
		return preferences.getInt(KEY_SAYAC, 0);
	}
	

	
	
	public static Long getLastUpdate() {
		return preferences.getLong(KEY_SAYAC, 0);
	}


	public static void setEditor(Editor editor) {
		StaticTools.editor = editor;
	}


	public static String getKEY_POS() {
		return KEY_POS;
	}


	public static void setKEY_POS(String kEY_POS) {
		KEY_POS = kEY_POS;
	}
	
	public static boolean isShowDialog() {
		return showDialog;
	}
	
	public static void setShowDialog(boolean showDialog) {
		StaticTools.showDialog = showDialog;
	}
	
	public static boolean isShowName() {
		return showName;
	}
	
	public static void setShowName(boolean showName) {
		StaticTools.showName = showName;
	}
}
