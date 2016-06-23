package mars.dev.androidviewshot;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

public class PicViewActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_view);
        String bmPath = getIntent().getStringExtra("picPath");
        Bitmap bm = getDiskBitmap(bmPath);
        Log.d("debug","bm path "+bmPath);
        if(bm!=null) {
            imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(bm);
        }

    }

    private Bitmap getDiskBitmap(String pathString)
    {
        Bitmap bitmap = null;
        try
        {
            File file = new File(pathString);
            if(file.exists())
            {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e)
        {
            // TODO: handle exception
        }


        return bitmap;
    }
}
