package com.example.showadvertisement.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;


public class Timer {

    private static String TAG = "Timer";

    private Handler mHandler;
    private HandlerThread mThread;
    private TimerHandler TimerHandler;

    public Timer() {
        mThread = new HandlerThread("TimeTask");
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), msg -> {
            try {
                handle(msg);

                Log.d(TAG, "Timer: ");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        });
    }

    public void work(String tag,TimerHandler handler,float delay){
        Data data = null;
        if (tag!=null&&handler!=null){
            data = new Data();
            data.tag = tag;
            data.handler = handler;
        }
        if (data!=null){
            if (delay==0){
                mHandler.obtainMessage(1,data).sendToTarget();
            } else {
                mHandler.sendMessageDelayed(mHandler.obtainMessage(
                        1, data), Math.round(delay * 1000));
            }
        }
    }

    private void handle(Message msg){
        Data data = (Data) msg.obj;
        if (data!=null){
            long t = System.currentTimeMillis();
            try {
                data.handler.handle(data);
                Log.d(TAG,data.tag+" 耗时："+(System.currentTimeMillis()-t));
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public static class Data {
        String tag;
        TimerHandler handler;
    }


    public interface TimerHandler {
        void handle(Data data);
    }

    public void quit() {
        mThread.quitSafely();
    }
}
