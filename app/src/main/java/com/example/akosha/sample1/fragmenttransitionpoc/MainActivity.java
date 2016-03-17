package com.example.akosha.sample1.fragmenttransitionpoc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Fragment2.onFragBackPressed {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private final String backStack = "backStack";
    private MyFragmentStack fragmentStack;
    private TextView helloText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add a fragment to the activity
        fragmentStack = new MyFragmentStack(this, fragmentManager, R.id.fragmentLayout, new MyFragmentStack.OnFragmentRemovedListener() {
            @Override
            public void onFragmentRemoved(Fragment fragment) {
                //// TODO: 16/03/16 write something useful
                Log.d(TAG, fragment.getClass().getSimpleName());
            }
        });


        if (savedInstanceState == null) {
            fragmentStack.push(new Fragment1());
        }
        Button button = (Button) findViewById(R.id.frag_trans_butt);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popAndReplace();
            }
        });

        helloText = (TextView) findViewById(R.id.hellotext);
        helloText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment3 fragment3 = new Fragment3();
                replace(fragment3);
            }
        });


//        addFragment();
    }

    public void push(Fragment fragment) {
        fragmentStack.push(fragment);
    }

    @Override
    public void onBackPressed() {
        //tell the current running fragment that backpressed has happened
        Fragment fragment = fragmentStack.peek();

        if (fragment instanceof Fragment2) {
            fragmentStack.pop();
            fragmentStack.push(new Fragment1());
        } else {
            if (!fragmentStack.pop()) {
                super.onBackPressed();
            } else {
                Log.d(TAG, "true");
            }
        }
        //// TODO: 17/03/16 add super.onBackPressed Functionality here

//        if (!fragmentStack.pop()) {
//            super.onBackPressed();
//        } else {
//            Log.d(TAG, "true");
//        }
    }

    public void replace(Fragment fragment) {
        fragmentStack.replace(fragment);
    }

    private void addFragment() {
        Fragment1 fragment1 = new Fragment1();
        //if want to add some data
//        fragmentManager.beginTransaction().add(R.id.fragmentLayout, fragment1)
//                .addToBackStack(backStack)
//                .commit();
//        fragmentManager.beginTransaction().add(R.id.fragmentLayout,fragment1)
//                .addToBackStack(backStack)
//                .commit();

        replace(fragment1);
    }

    private void popAndReplace() {
//        fragmentManager.popBackStack(backStack, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Fragment2 fragment2 = new Fragment2();
//        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment2)
//                .addToBackStack(backStack)
//                .commit();
        replace(fragment2);
    }

    @Override
    public void onFragBackPressed(Fragment fragment) {
        //handle on backPressed
//        fragmentManager.beginTransaction().replace(R.id.fragmentLayout, fragment)
//                .addToBackStack(backStack)
//                .commit();
        replace(fragment);
    }


}
