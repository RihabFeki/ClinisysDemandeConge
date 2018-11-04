package com.csys.clinisys.demandeconge.helper;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Utility methods for manipulating the onscreen keyboard
 */
public class KeyboardUtil {
    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Activity activity) {
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            // inputMethodManager.toggleSoftInput(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN,
            // 0);
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);

            MessageLog.MessageInfo(new Exception().getStackTrace(), String.valueOf("focusedView"));
        } else {
            MessageLog.MessageInfo(new Exception().getStackTrace(), String.valueOf("focusedView"));
        }
    }

    /**
     * Shows the soft keyboard
     */
    public static void showSoftKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        view.requestFocus();
        inputMethodManager.showSoftInput(view, 0);
    }

}
