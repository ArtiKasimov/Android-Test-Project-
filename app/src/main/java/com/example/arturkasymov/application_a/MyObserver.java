package com.example.arturkasymov.application_a;

import android.annotation.SuppressLint;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

@SuppressLint("NewApi")
class MyObserver extends ContentObserver {
    public MyObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange) {
        this.onChange(selfChange, null);
        //Toast.makeText(SecondFragment.secondFragment.getContext(),"qwer",Toast.LENGTH_SHORT).show();
        SecondFragment.sms();
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        SecondFragment.sms();
        // do s.th.
        // depending on the handler you might be on the UI
        // thread, so be cautious!
    }
}