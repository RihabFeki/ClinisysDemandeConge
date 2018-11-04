package com.csys.clinisys.demandeconge.helper;


import android.content.Context;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.csys.clinisys.demandeconge.R;

public class Alerte {

    static Toast toast;

    public static void playSoundNotification(Context context) {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getAlerte(Context context, Boolean statu, String msg) {

        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int couleur = Color.parseColor("#FFFFFF");
        int image = 0;
        if (statu == true) {
            couleur = Color.parseColor("#388E3C");
            image = R.drawable.like;
        } else {
            couleur = Color.parseColor("#B40431");
            image = R.drawable.cancel;
        }
        LinearLayout layout;
        layout = new LinearLayout(context);
        layout.setBackgroundColor(couleur);

        TextView tv = new TextView(context);
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(18);
        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setPadding(10, 10, 10, 10);
        tv.setText(msg);

        ImageView img = new ImageView(context);
        img.setPadding(10, 10, 10, 10);
        img.setImageResource(image);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
        img.setLayoutParams(layoutParams);

        layout.addView(tv);
        layout.addView(img);

        try {
            toast.cancel();
        } catch (Exception e) {
        }
        toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.setGravity(Gravity.RIGHT, 0, -300);
        toast.show();

    }

    public static void getToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
