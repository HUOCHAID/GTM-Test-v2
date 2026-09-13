package com.quicksupport.market;

import android.util.Log;
import androidx.annotation.Keep;

import com.google.android.gms.tagmanager.CustomTagProvider;

import java.util.Map;

@Keep
public class FirstStartTag implements CustomTagProvider {

    @Override
    public void execute(Map<String, Object> parameters) {
        Log.d("GTM_TEST", "FirstStartTag executed");
    }
}
