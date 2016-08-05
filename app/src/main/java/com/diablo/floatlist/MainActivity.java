package com.diablo.floatlist;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        FragmentManager fragmentManager = getSupportFragmentManager();//v4 getSupportFragmentManager( )
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        InformationsFragment firstFragment = new InformationsFragment();
        transaction.replace(R.id.layout, firstFragment);
        transaction.commit();
    }

}
