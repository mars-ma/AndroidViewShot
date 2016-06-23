package mars.dev.androidviewshot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import mars.dev.lib.ViewShotUtil;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    Button btnMake;
    LinearLayout layContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layContainer = (LinearLayout)findViewById(R.id.layContainer);
        btnMake = (Button)findViewById(R.id.btnMake);
        btnMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = ViewShotUtil.getViewShot(layContainer);
                String filePath = getCacheDir().getAbsolutePath()+"/";
                String fileName = System.currentTimeMillis()+".jpg";
                saveBitmap(bm,filePath,fileName);
                Intent intent = new Intent(MainActivity.this,PicViewActivity.class);
                intent .putExtra("picPath",filePath+fileName);
                startActivity(intent);
                layContainer.requestLayout();
            }
        });
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.requestLayout();
            }
        });
        webView.loadUrl("http://www.baidu.com");
    }

    public static void saveBitmap(Bitmap bm, String path, String pic_name) {
        if(bm==null){
            return;
        }
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File bitmap_file = new File(dir, pic_name);
        if (bitmap_file.exists()) {
            bitmap_file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(bitmap_file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
