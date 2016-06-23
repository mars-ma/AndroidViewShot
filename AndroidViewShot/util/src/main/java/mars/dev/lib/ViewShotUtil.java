package mars.dev.lib;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;

import java.io.File;

/**
 * Created by Mars on 2016/6/23.
 */
public class ViewShotUtil {
        public static Bitmap getViewShot(View view){
            if(view.getMeasuredWidth()<=0||view.getMeasuredHeight()<=0){
                return null;
            }
            Bitmap b = Bitmap.createBitmap(view.getMeasuredWidth() , view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            view.layout(0, 0, view.getLayoutParams().width, view.getLayoutParams().height);
            view.draw(c);
            return b;
        }

}
