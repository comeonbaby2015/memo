package judgelayout.android_native.demo;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    public static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

    }

    @OnClick(R.id.showLayout)
    public void showLayout(Button showLayout) {
        // 判断当前加载的是哪个layout？或者屏幕的尺寸？
        //way2();
        //way3();
        //way4();
        printCycle();

    }

    private void printCycle() {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
            Log.d(TAG, i + 1 + "");
        }
    }

    private void way1(Button showLayout) {
        Toast.makeText(this, showLayout.getTag().toString(), Toast.LENGTH_SHORT).show();
    }


    // http://stackoverflow.com/questions/11205020/how-can-i-detect-which-layout-is-selected-by-android-in-my-application
    private void way2() {
        int size = getResources().getConfiguration().screenLayout;

        if ((size & Configuration.SCREENLAYOUT_SIZE_XLARGE) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(this, "SCREENLAYOUT_SIZE_XLARGE", Toast.LENGTH_SHORT).show();
        } else if ((size & Configuration.SCREENLAYOUT_SIZE_LARGE) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "SCREENLAYOUT_SIZE_LARGE", Toast.LENGTH_SHORT).show();

        } else if ((size & Configuration.SCREENLAYOUT_SIZE_NORMAL) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "SCREENLAYOUT_SIZE_NORMAL", Toast.LENGTH_SHORT).show();
        }
    }

    private void way3() {
        Toast.makeText(this, R.string.screen_size, Toast.LENGTH_SHORT).show();
    }

    private void way4() {
        Toast.makeText(this, isTablet(this) ? "true" : "false", Toast.LENGTH_SHORT).show();
    }

    public boolean isTablet(Context context) {
        boolean result = false;
        try {
            DisplayMetrics dm = getDisplayMetrics(context);
            float screenWidth = dm.widthPixels / dm.xdpi;
            float screenHeight = dm.heightPixels / dm.ydpi;
            double size = Math.sqrt(Math.pow(screenWidth, 2) + Math.pow(screenHeight, 2));
            // Tablet devices should have a screen size greater than 7 inches
            result = size >= 7;
        } catch (Exception e) {
            Log.e(TAG, "Problem getting values: " + e.getMessage(), e);
        }
        return result;
    }

    public DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }


}
