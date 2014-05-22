package com.example.thread_asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageView iv;
	Button b1, b2;
	Bitmap image;
	Drawable draw;
	imageLoader imageloader;
	ProgressBar pb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		b2 = (Button) findViewById(R.id.b2);
		b1 = (Button) findViewById(R.id.b1);
		b2.setOnClickListener(this);
		b1.setOnClickListener(this);
		pb = (ProgressBar) findViewById(R.id.pb);
		pb.setVisibility(ProgressBar.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v.getId() == R.id.b2) {
			/*
			 * image = BitmapFactory .decodeResource(getResources(),
			 * R.drawable.qaz); iv.setImageBitmap(image);
			 */
			imageloader = new imageLoader();
			imageloader.execute(R.drawable.qaz);
		}

		else if (v.getId() == R.id.b1) {
			Toast.makeText(MainActivity.this, "how u doing..??",
					Toast.LENGTH_SHORT).show();
		}
	}

	class imageLoader extends AsyncTask<Integer, Integer, Bitmap> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pb.setVisibility(ProgressBar.VISIBLE);
		}

		@Override
		protected Bitmap doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			image = BitmapFactory.decodeResource(getResources(), params[0]);

			for (int i = 0; i < 11; ++i) {// to stimulate a heavy process
				try {
					Thread.sleep(500);
					publishProgress(i * 10);
				} catch (InterruptedException e) { // TODO Auto-generated catch
													// block
					e.printStackTrace();
				}
			}

			return image;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			pb.setProgress(values[0]);
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pb.setVisibility(ProgressBar.INVISIBLE);
			iv.setImageBitmap(result);
		}
	}

}
