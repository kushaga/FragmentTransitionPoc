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

//fragment transition -- index to tag
//tag - fragment class name
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

    public void push(Fragment fragment) {
        //push the fragment
        Fragment firstElement = peek();

        if (firstElement != null) {
            fragmentManager.beginTransaction()
                    .add(viewId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(String.valueOf(fragmentManager.getBackStackEntryCount() + 1))// give the fragment added name i.e. it's count in stack
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(viewId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(indexToTag(0))
                    .commit();
        }
    }

    public boolean pop() {
        //pop the fragments
        if (fragmentManager.getBackStackEntryCount() == 0) {
            //activity back pressed when no element is there should call super.onbackpressed();
            return false;
        }
        Fragment popedElement = peek();
        fragmentManager.popBackStackImmediate();

        if (fragmentRemovedListener != null) {
            fragmentRemovedListener.onFragmentRemoved(popedElement);
        }
        return true;
    }

    public void replace(Fragment fragment) {
        //replace the current fragment in the backstack
        List<Fragment> fragments = getFragments();

        try {
            //give call to all the fragments that have been removed
            if (fragmentRemovedListener != null) {
                int counter = 0;
                for (Fragment frag : fragments) {
                    if (frag.getTag() == fragment.getClass().getSimpleName()) {
                        break;
                    }
                    counter++;
                }

                int i = 0;
                for (Fragment frag : fragments) {
                    if (!(i < counter)) {
                        break;
                    }
                    fragmentRemovedListener.onFragmentRemoved(frag);
                }
            }
            //pop the element with the same name
            fragmentManager.popBackStack(fragment.getClass().getSimpleName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fragmentManager.getBackStackEntryCount() != 0) {
            fragmentManager.beginTransaction()
                    .replace(viewId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(indexToTag(fragmentManager.getBackStackEntryCount() + 1))
                    .commit();
        } else {
            fragmentManager.beginTransaction()
                    .add(viewId, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(indexToTag(0));
        }

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
