package embedded.kocaeli.edu.tr.embeddedmuzeuygulamasi.lit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;


import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.List;

class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<ImageView> imageViewReference;

	private static Bitmap mBitmap = null;

	String imagePath;
	
	List<Bitmap> bitmapList;
	
	Kvideo kvideo;
	

	public ImageDownloaderTask(ImageView imageView,Kvideo kvideo) {
		imageViewReference = new WeakReference<ImageView>(imageView);
		this.kvideo = kvideo;
	}

	@Override
	// Actual download method, run in the task thread
	protected Bitmap doInBackground(String... params) {
		// params comes from the execute() call: params[0] is the url.
		
		//System.out.println(" httttt "+params[0]);
		return downloadBitmap(params[0]);
	}

	@Override
	// Once the image is downloaded, associates it to the imageView
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (imageViewReference != null) {
			ImageView imageView = imageViewReference.get();
			if (imageView != null) {

				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
					this.kvideo.setBitmap(bitmap);
					//diziItem.setBitmap(bitmap);
					try {
					//OutputStream stream = new FileOutputStream(imagePath);
					//bitmap.compress(Bitmap.CompressFormat.PNG, 85, stream);
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
				//	imageView.setImageDrawable(imageView.getContext().getResources()
						//	.getDrawable(R.drawable.yrni));
				}
			}

		}
	}

	static Bitmap downloadBitmap(String url) {



		ImageRequest ir = new ImageRequest(url,
				new Response.Listener<Bitmap>() {
					@Override
					public void onResponse(Bitmap response) {
						mBitmap = response;

					}
				},0,0,null,null);

		return mBitmap;
	}

}