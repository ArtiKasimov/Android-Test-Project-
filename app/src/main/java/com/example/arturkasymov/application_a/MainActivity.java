package com.example.arturkasymov.application_a;

import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import  android.support.design.widget.TabLayout;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String CONTENT_URI = "content://com.misha.database.provider.MyContentProvider/refs";

    private FrameLayout simpleFrameLayout;
    private TabLayout tabLayout;
    private static MainActivity mainActivity;
    private MyObserver myObserver = new MyObserver(new Handler());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        simpleFrameLayout = (FrameLayout) findViewById(R.id.simpleFrameLayout);
        tabLayout = (TabLayout) findViewById(R.id.simpleTabLayout);

        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText(R.string.firstTab);
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText(R.string.secondTab);
        tabLayout.addTab(secondTab);

        Fragment fragment = new FirstFragment();
        changeFragment(fragment);

        // perform setOnTabSelectedListener event on TabLayout (deprecated)
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new FirstFragment();
                        break;
                    case 1:
                        fragment = new SecondFragment();
                        break;
                }
                changeFragment(fragment);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void changeFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getContentResolver().
                registerContentObserver(
                        Uri.parse(CONTENT_URI),
                        true,
                        myObserver);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getContentResolver().
                unregisterContentObserver(myObserver);
    }

    public static void massage(){
        Toast.makeText(mainActivity, R.string.massageDel,Toast.LENGTH_SHORT).show();
    }
}
