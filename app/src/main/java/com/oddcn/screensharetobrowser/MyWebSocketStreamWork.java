package com.oddcn.screensharetobrowser;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Created by kason_zhang on 2/25/2017.
 * Team:TrendMicro VMI
 */

public class MyWebSocketStreamWork implements Runnable {
    private static final String TAG = "MyWebSocketStreamWork";
    private byte byteBuffer[] = new byte[1024];//缓冲字节数组
    private ByteArrayOutputStream byteArrayOutputStream;

    public MyWebSocketStreamWork(ByteArrayOutputStream byteArrayOutputStream) {
        this.byteArrayOutputStream = byteArrayOutputStream;
    }

    @Override
    public void run() {
        //建立Socket连接
        try {
            ByteArrayInputStream byteArrayInputStream =
                    new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            while (byteArrayInputStream.read(byteBuffer) != -1) {
                MyServer.get().broadcast(byteBuffer);
            }
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            Log.i(TAG, "Socket Stream work run exception");
            e.printStackTrace();
        }
    }
}
