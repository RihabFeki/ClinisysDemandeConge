package com.csys.clinisys.demandeconge.helper;


import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class IconFrontelloView extends AppCompatTextView {

    public IconFrontelloView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public IconFrontelloView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public IconFrontelloView(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fontello.ttf");
            setTypeface(tf);
        }
    }
}
