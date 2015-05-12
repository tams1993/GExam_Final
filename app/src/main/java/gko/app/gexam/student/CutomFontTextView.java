package gko.app.gexam.student;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by MR.T on 5/12/2015.
 */
public class CutomFontTextView extends TextView {

    public CutomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CutomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CutomFontTextView(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "phetsarath.ttf");
        setTypeface(tf);
    }

}