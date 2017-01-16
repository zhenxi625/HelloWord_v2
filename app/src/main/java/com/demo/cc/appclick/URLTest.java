package com.demo.cc.appclick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cc on 2016/11/10.
 */

public class URLTest extends AppCompatActivity {

    ImageView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.urltest);

        show = (ImageView) findViewById(R.id.show);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork()
                .penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                .penaltyLog().penaltyDeath().build());

        try {
            //定义URL对象
//            URL url = new URL("http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg");
            URL url = new URL("http://img5.duitang.com/uploads/item/201601/28/20160128123736_xsi5e.jpeg");
            //打开url对应的资源的输入流
            InputStream inputStream = url.openStream();
            //从inputStream中解析图片
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            //使用ImageView显示该图片
            show.setImageBitmap(bitmap);
            inputStream.close();

            //再次打开url对应的资源的输入流
            inputStream = url.openStream();
            //打开手机文件对应的输出流
            OutputStream outputStream = openFileOutput("轻轨2.jpg", MODE_WORLD_READABLE);
            byte[] buff = new byte[1024];
            int hasRead = 0;
            //资源下载到本地
            while ((hasRead = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, hasRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
