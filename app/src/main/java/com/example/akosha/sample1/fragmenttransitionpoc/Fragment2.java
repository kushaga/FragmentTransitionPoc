package com.example.akosha.sample1.fragmenttransitionpoc;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kushagarlall on 16/03/16.
 */
public class Fragment2 extends Fragment implements FragmentStack.OnBackPressedHandlingFragment {

    private static final String TAG = Fragment2.class.getSimpleName();

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.d(TAG, "onsaveInstance");
//    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        Log.d(TAG, "onDestroyView");
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
        ((MainActivity) this.getActivity()).onFragBackPressed(new Fragment1());
    }


    @Override
    public boolean onBackPressed() {
        Log.d(TAG, "on back pressed");
        ((MainActivity) this.getActivity()).push(new Fragment1());
        return true;
    }

    public interface onFragBackPressed {
        public void onFragBackPressed(Fragment fragment);
    }
}

