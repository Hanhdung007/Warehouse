package com.example.test_flutter;

import io.flutter.embedding.android.FlutterActivity;
import android.content.Context;
import androidx.multidex.MultiDex;
public class MainActivity extends FlutterActivity {
     @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
