package com.dfqc.mydevice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.multidex.MultiDex;
import android.tw.john.TWClient;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends Activity {
    private static final String TAG = "MyActivity";
    private TWClient mTWClient = null;
    private TextView tv;
	private String bb;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(MainActivity.this,"111",Toast.LENGTH_LONG).show();
            switch(msg.what) {
                case TWClient.R_CAN:
                    Log.e(TAG,"AAAAAA");
                    byte[] objData = (byte[]) msg.obj;
                    for (int i = 0;i < objData.length;i++){
                        tv.setText(msg.arg1+"---"+msg.arg2+"---" + objData[i]);
                        Log.e(TAG,msg.arg1+"---"+msg.arg2+"---" + objData[i]);
                }

                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        mTWClient = TWClient.open(TWClient.T_CAN, null, TAG, mHandler);
        Log.e(TAG,"bbbbb");
        new Thread(new Runnable() {
            @Override
            public void run() {
//                downLoadFile("http://file.liqucn.com/cdn_not_support_alias/apkinstaller_");
//                openFile(downLoadFile("http://file.liqucn.com/cdn_not_support_alias/apkinstaller_"));
            }
        });


    }
    //下载apk程序代码

//    protected File downLoadFile(String httpUrl) {
//        // TODO Auto-generated method stub
//        final String fileName = "updata.apk";
//        File tmpFile = new File("/mnt/sdcard/update");
//        if (!tmpFile.exists()) {
//            tmpFile.mkdir();
//        }
//        final File file = new File("/mnt/sdcard/update/" + fileName);
//
//        try {
//            URL url = new URL(httpUrl);
//            try {
//                HttpURLConnection conn = (HttpURLConnection) url
//                        .openConnection();
//                InputStream is = conn.getInputStream();
//                FileOutputStream fos = new FileOutputStream(file);
//                byte[] buf = new byte[256];
//                conn.connect();
//                double count = 0;
//                if (conn.getResponseCode() >= 400) {
//                    Toast.makeText(MainActivity.this, "连接超时", Toast.LENGTH_SHORT)
//                            .show();
//                } else {
//                    while (count <= 100) {
//                        if (is != null) {
//                            int numRead = is.read(buf);
//                            if (numRead <= 0) {
//                                break;
//                            } else {
//                                fos.write(buf, 0, numRead);
//                            }
//
//                        } else {
//                            break;
//                        }
//
//                    }
//                }
//
//                conn.disconnect();
//                fos.close();
//                is.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//
//                e.printStackTrace();
//            }
//        } catch (MalformedURLException e) {
//            // TODO Auto-generated catch block
//
//            e.printStackTrace();
//        }
//
//        return file;
//    }
//    //打开APK程序代码
//
//    private void openFile(File file) {
//        // TODO Auto-generated method stub
//        Log.e("OpenFile", file.getName());
//        Intent intent = new Intent();
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(file),
//                "application/vnd.android.package-archive");
//        startActivity(intent);
//    }
    @Override
    protected void onDestroy() {
        mTWClient.close(TAG);
        mTWClient = null;
        super.onDestroy();
    }
    /**
          * 分包
          * @param base
          */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
