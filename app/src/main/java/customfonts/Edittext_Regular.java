package customfonts;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class Edittext_Regular extends EditText {
    public Edittext_Regular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public Edittext_Regular(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Edittext_Regular(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "myfonts/Roboto-Regular.ttf");
            setTypeface(tf);
        }
    }

}