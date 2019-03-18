package com.grab.grabnewstestapp.ui.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Switch;
import android.widget.TextView;

import com.grab.grabnewstestapp.R;

public class NewsTextView extends TextView {
    public NewsTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        style(context, attrs);
    }

    public NewsTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        style(context, attrs);
    }

    private void style(Context context, AttributeSet attrs) {

        int fontName = 0;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
        int cf = a.getInteger(R.styleable.CustomFontTextView_fontName, 0);
        switch (cf) {
            case 1:
                fontName = R.string.montserrat_bold;
                break;
            case 2:
                fontName = R.string.montserrat_light;
                break;
            case 3:
                fontName = R.string.montserrat_medium;
                break;
            case 4:
                fontName = R.string.montserrat_regular;
                break;
            default:
                fontName = R.string.montserrat_regular;
        }

        String customFont = getContext().getResources().getString(fontName);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "font/" + customFont + ".otf");
        setTypeface(tf);
        a.recycle();
    }
}
