package com.dkl.auser.volley2;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
//import android.icu.util.Output;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv2;
    private TextView tv;
    private ProgressBar pb;
    private ImageView img;
    private ProgressBar pb2;
    private int readSum=0;
    private Handler handler;
    private File imgFile;
    private ImageView img2;
//    File imgFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    img = (ImageView)findViewById(R.id.imageView);
    pb2 = (ProgressBar)findViewById(R.id.progressBar);
        pb = (ProgressBar)findViewById(R.id.progressBar2);
        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        imgFile = new File(getFilesDir() + File.separator + "photo.jpg");

    }
//    public void clickDown(View view){
//        String uri = "http://images.all-free-download.com/images/graphiclarge/butterfly_flower_01_hd_pictures_166973.jpg";
//        pb.setVisibility(View.VISIBLE);
//
//        try {
//            URL url = new URL(uri);
//            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//conn.connect();
//final int fullSize = conn.getContentLength();
////            InputStream is = (InputStream)img.getImageMatrix();
//            InputStream is = conn.getInputStream();
//            ByteArrayOutputStream os = new ByteArrayOutputStream();
//            byte[] b= new byte[1024];
//            int readSize;
//            while((readSize=is.read(b))!=-1){
////                os.write(readSize);
//                os.write(b,0,readSize);
//readSum += readSize;
//
//                Log.d("NET","read:"+readSum);
//runOnUiThread(new Runnable() {
//    @Override
//    public void run() {
//        tv.setText(String.valueOf(readSum));
//        tv2.setText(String.valueOf(readSum/fullSize*100));
//        pb2.setProgress((readSum/fullSize)*100);
//    }
//});
//
//            }
//byte result[] = os.toByteArray();
//            final Bitmap bmp = BitmapFactory.decodeByteArray(result,0,result.length);
//
//            Log.d("NET","finished");
//
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    img.setImageBitmap(bmp);
//                    pb.setVisibility(View.INVISIBLE);
//                }
//            });
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
//public void clickDown(View v)
//{
//    pb.setVisibility(View.VISIBLE);
//    pb2.setVisibility(View.VISIBLE);
//    tv.setVisibility(View.VISIBLE);
//    tv2.setVisibility(View.VISIBLE);
//    readSum = 0;
//    new Thread(){
//        @Override
//        public void run() {
//            super.run();
//            try {
//
//                URL url = new URL("https://www.cleverfiles.com/howto/wp-content/uploads/2016/08/mini.jpg");
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.connect();
//                final int fullSize = conn.getContentLength();
//
//                InputStream is = conn.getInputStream();
//                ByteArrayOutputStream os = new ByteArrayOutputStream();
//                byte b[] = new byte[1024];
//                int readSize;
//                while ((readSize = is.read(b)) != -1)
//                {
//                    os.write(b, 0, readSize);
//                    readSum += readSize;
//                    Log.d("NET", "read:" + readSum);
//
//
//                    handler = new Handler();
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            tv.setText(String.valueOf(readSum+"kb"));
//                            tv2.setText(String.valueOf(100 * readSum / fullSize));
//                            pb2.setProgress(100 * readSum / fullSize);
//                        }
//                    });
//
////                    runOnUiThread(new Runnable() {
////                        @Override
////                        public void run() {
////                            tv.setText(String.valueOf(readSum+"kb"));
////                            tv2.setText(String.valueOf(100 * readSum / fullSize));
////                            pb2.setProgress(100 * readSum / fullSize);
////                        }
////                    });
//                }
//                byte result[] = os.toByteArray();
//                final Bitmap bmp = BitmapFactory.decodeByteArray(result, 0, result.length);
//                Log.d("NET", "Image Finish");
//
//                FileOutputStream fos = new FileOutputStream(imgFile);
//                fos.write(result);
//                fos.close();
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        img.setImageBitmap(bmp);
//
//                        pb.setVisibility(View.INVISIBLE);
//                        pb2.setVisibility(View.INVISIBLE);
//                    }
//                });
//                is.close();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }.start();
//}
//public void clickRead(View v)
//{
//    Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//    img2.setImageBitmap(bitmap);
//}


    public void clickDown(View v)
    {
        pb.setVisibility(View.VISIBLE);
        readSum = 0;
        MyTask task = new MyTask();
        task.execute("http://images.all-free-download.com/images/graphiclarge/butterfly_flower_01_hd_pictures_166973.jpg");
    }
class MyTask extends AsyncTask<String, Integer, Bitmap>
{

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            final int fullSize = conn.getContentLength();

            InputStream is = conn.getInputStream();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte b[] = new byte[1024];
            int readSize;
            while ((readSize = is.read(b)) != -1)
            {
                os.write(b, 0, readSize);
                readSum += readSize;
                publishProgress(readSum, 100 * readSum / fullSize);
            }
            byte result[] = os.toByteArray();
            bmp = BitmapFactory.decodeByteArray(result, 0, result.length);
            FileOutputStream fos = new FileOutputStream(imgFile);
            fos.write(result);
            fos.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tv.setText(String.valueOf(values[0]));
        tv2.setText(String.valueOf(values[1]));
        pb2.setProgress(values[1]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        img.setImageBitmap(bitmap);
    }
}





}
