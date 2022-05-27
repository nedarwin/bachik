package com.example.tamagtom;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Stat extends Activity {

    private String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stat);
        TextView restv = (TextView)this.findViewById(R.id.results);
        String resStr = "";
        restv.setText(resStr);
    }
}