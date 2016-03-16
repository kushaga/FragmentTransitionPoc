package com.example.akosha.sample1.fragmenttransitionpoc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kushagarlall on 16/03/16.
 */
public class Fragment3 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag2_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.frag2text);
        textView.setText("Fragment 3");
        return view;
    }
}
