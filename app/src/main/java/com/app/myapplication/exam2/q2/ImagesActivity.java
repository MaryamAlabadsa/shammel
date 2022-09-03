package com.app.myapplication.exam2.q2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImagesActivity extends AppCompatActivity implements View.OnClickListener {
    Button image1, image2, image3, image4;
    ImageView imageView;
    String downloadUrl1 = "https://images.pexels.com/photos/1054289/pexels-photo-1054289.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";
    String downloadUrl2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR3QAaeLe6BeXqfZkQpu3aMLOY91e-Y43MyCWA8Vh7JCqZo--vtxw";
    String downloadUrl3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfp-VHPX16y4EVph_I5b5rK-VFPzIrAALwylo-OSYjOzYVPzMRwA";
    String downloadUrl4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQhL-jHCVQMW9KyY-70hFgaRtQ5B1KAPxu-5ZPhFKZiCmL8y-8q";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        image1 = findViewById(R.id.button_image1);
        image2 = findViewById(R.id.button_image2);
        image3 = findViewById(R.id.button_image3);
        image4 = findViewById(R.id.button_image4);
        imageView = findViewById(R.id.imageView);
        image1.setOnClickListener(this::onClick);
        image2.setOnClickListener(this::onClick);
        image3.setOnClickListener(this::onClick);
        image4.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_image1:
                new showImage().execute(downloadUrl1);
                break;
            case R.id.button_image2:
                new showImage().execute(downloadUrl2);
                break;
            case R.id.button_image3:
                new showImage().execute(downloadUrl3);
                break;
            case R.id.button_image4:
                new showImage().execute(downloadUrl4);
                break;
        }
    }

    private class showImage extends AsyncTask<String, String, Bitmap> {

        private ProgressDialog progressDialog;
        private HttpURLConnection httpURLConnection;
        private InputStream inputStream;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(ImagesActivity.this);
            progressDialog.setTitle("Download Image");
            progressDialog.setMessage("DownLoading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
//                Toast.makeText(ImagesActivity.this, strings[0] + "", Toast.LENGTH_SHORT).show();
//                URL urlConnection = new URL(strings[0]);
//                HttpURLConnection connection = (HttpURLConnection) urlConnection
//                        .openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                return myBitmap;
                URL newurl = new URL(strings[0]);
                Bitmap myBitmap = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
                return myBitmap;
            } catch (Exception e) {
                Log.e("kkkkkkkkk","aaaaaa");
                e.printStackTrace();
            }
            return null;
        }

//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            progressDialog.setProgress(values[0]);
//        }


        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            Toast.makeText(ImagesActivity.this, result + "ssssssssss", Toast.LENGTH_SHORT).show();
            imageView.setImageBitmap(result);
        }
    }

}