package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.graphics.Bitmap;

import org.json.JSONException;
import org.json.JSONObject;

public class Kvideo {
	
	String videoid;
	String isim;
	int duration;
	int cesit;
	String resim_url;
	long id;
	long turId;
	
	Bitmap bitmap;

	
	public static String KEY_ID ="id";
	public static String KEY_VIDEOID ="videoid";
	public static String KEY_ISIM="isim";
	public static String KEY_RESIM_URL="resim_url";
	
	
	public static final String KEY_CESIT = "cesit";
	
	
	
	public static Kvideo fromJson(JSONObject o) throws JSONException {
		Kvideo k = new  Kvideo();
		k.id = o.getLong("id");
		k.videoid = ""+o.getString(KEY_VIDEOID);
		k.isim = ""+o.getString(KEY_ISIM);
		k.cesit = 0+o.getInt(KEY_CESIT);
		
		return k;
	}
	
	
	public Kvideo(String videoid, String isim,int duratio) {
		super();
		this.videoid = videoid;
		this.isim = isim;
		this.duration = duratio;
	}
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public Kvideo() {
		// TODO Auto-generated constructor stub
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public int getCesit() {
		return cesit;
	}
	public void setCesit(int cesit) {
		this.cesit = cesit;
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	
}
