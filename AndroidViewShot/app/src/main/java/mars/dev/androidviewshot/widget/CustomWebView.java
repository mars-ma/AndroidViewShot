package mars.dev.androidviewshot.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Maxuanwei on 2016/6/23.
 */
public class CustomWebView extends WebView {
    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxSize = Integer.MAX_VALUE>>2;
        int mode = MeasureSpec.AT_MOST;
        super.onMeasure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(maxSize,mode));
    }
}
