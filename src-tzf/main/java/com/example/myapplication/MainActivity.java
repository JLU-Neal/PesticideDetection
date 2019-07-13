package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private String TAG = "RGBActivity";
    /**
     * 文件夹下图片的真实路径
     */
    static private String scanpath;
    /**
     * 所有图片的名字
     */
    static public String[] allFiles;
    /**
     * 想要查找的文件夹
     */
    static private File folder;
    private List<ImageView> mItems = new ArrayList<>();
    private List<Double>GreyList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void detect_RGB(View v)
    {
        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString() + "/nongyao/");
        //folder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
        //folder = new File("DCIM/Camera/");
        //将文件夹下所有文件名存入数组
        if (folder.list() != null) {
            allFiles = folder.list();
            //遍历数组
            for (int i = 0; i < allFiles.length; i++) {
                scanpath = folder + "/" + allFiles[i];
                //Log.i(TAG, "initData: " + scanpath);
                //将文件转为bitmap如果为空则不是图片文件
                Bitmap bitmap = BitmapFactory.decodeFile(scanpath);
                //图片写入适配器
                //ImageView view = new ImageView(this);
                //view.setImageBitmap(bitmap);
                //view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                //mItems.add(view);
                int x = 1;
                int y = 1;
                int color = bitmap.getPixel(x, y);
                int r = Color.red(color);
                int g = Color.green(color);
                int b = Color.blue(color);
                int a = Color.alpha(color);
                double grey=(double)r*0.299+(double)g*0.587+(double)b*0.114;
                //每当检测完一张图片，添加该图片的灰度值到队列里
                GreyList.add(grey);
                String message="r=" + r + ",g=" + g + ",b=" + b+",grey="+grey;
                Log.i(TAG, message);
                TextView txv=(TextView) findViewById(R.id.txv);
                txv.setText(message);
            }
        }else {
            Log.i(TAG,"Image not found");
            TextView txv=(TextView) findViewById(R.id.txv);
            txv.setText("Image not found");
        }



        //输出每张照片的灰度值
        for(int i=0;i<GreyList.size();i++)
        {
            Log.i(TAG,GreyList.get(i).toString());
        }



    }

}
