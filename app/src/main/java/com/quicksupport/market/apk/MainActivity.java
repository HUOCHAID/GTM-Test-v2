package com.quicksupport.market.apk;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends Activity {

    private static final String PREFS = "gtm_test_state";
    private static final String KEY_SENT = "b_first_start_sent";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        int pad = (int) (24 * getResources().getDisplayMetrics().density);
        layout.setPadding(pad, pad, pad, pad);

        TextView title = new TextView(this);
        title.setTextSize(22f);
        title.setText("GTM / Firebase 首次启动测试");
        layout.addView(title);

        TextView status = new TextView(this);
        status.setTextSize(16f);
        status.setPadding(0, pad, 0, 0);
        layout.addView(status);

        setContentView(layout);

        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean alreadySent = prefs.getBoolean(KEY_SENT, false);

        if (!alreadySent) {
            FirebaseAnalytics.getInstance(this)
                    .logEvent("b_first_start", null);

            prefs.edit().putBoolean(KEY_SENT, true).apply();
            status.setText("已发送 Firebase 事件：b_first_start\n等待 GTM 触发 FirstStartTag。");
        } else {
            status.setText("b_first_start 已在本次安装中发送过。\n如需重测，请先卸载再重新安装 APK。");
        }
    }
}
