package com.example.akosha.sample1.fragmenttransitionpoc;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kushagarlall on 16/03/16.
 */
public class MyFragmentStack {
    //handles fragment stack for my activity

    private final Activity activity;
    private final FragmentManager fragmentManager;
    private final int viewId;
    @Nullable
    private OnFragmentRemovedListener fragmentRemovedListener;

    public interface OnFragmentRemovedListener {
        void onFragmentRemoved(Fragment fragment);
    }

    public MyFragmentStack(Activity activity, FragmentManager fragmentManager, int viewId, OnFragmentRemovedListener listener) {
        this.activity = activity;
        this.fragmentManager = fragmentManager;
        this.viewId = viewId;
        this.fragmentRemovedListener = listener;
    }

    private void pop() {

    }

    private void push(Fragment fragment) {

    }


    public Fragment peek() {
        return fragmentManager.findFragmentById(viewId);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>(fragmentManager.getBackStackEntryCount() + 1);
        for (int i = 0; i < fragmentManager.getBackStackEntryCount() + 1; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(indexToTag(i));
            if (fragment != null)
                fragments.add(fragment);
        }
        return fragments;
    }

    //tag each fragment
    private String indexToTag(int index) {
        return Integer.toString(index);
    }
}
