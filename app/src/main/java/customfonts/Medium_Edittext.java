package customfonts;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class Medium_Edittext extends EditText {
    public Medium_Edittext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Medium_Edittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Medium_Edittext(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "myfonts/Roboto-Medium.ttf");
            setTypeface(tf);
        }
    }

}