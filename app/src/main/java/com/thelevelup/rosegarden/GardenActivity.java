package com.thelevelup.rosegarden;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class GardenActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garden);
    }
}
