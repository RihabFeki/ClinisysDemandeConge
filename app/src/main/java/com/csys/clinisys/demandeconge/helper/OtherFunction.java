package com.csys.clinisys.demandeconge.helper;

import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by admin on 26/04/2017.
 */

public class OtherFunction {
    public static void strictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @NonNull
    public static Spanned fromHtml(@NonNull String source) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
