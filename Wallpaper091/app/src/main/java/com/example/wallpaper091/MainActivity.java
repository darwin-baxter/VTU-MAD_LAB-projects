package com.example.wallpaper091;
import androidx.appcompat.app.AppCompatActivity;
import android.app.WallpaperManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;import android.widget.Button;
import java.io.IOException;import java.util.Timer;
import java.util.TimerTask;
public class MainActivity extends AppCompatActivity
{
    int images[] = new int[]{
            R.drawable.walls1,
            R.drawable.walls2,
            R.drawable.walls3
    };
    Button btn;int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Timer().schedule(new ChangeWallpaper(),0, 30000); }
        });
    }
    class ChangeWallpaper extends TimerTask{
        @Override
        public void run() {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(getBaseContext());
            try {
                wallpaperManager.setBitmap( BitmapFactory.decodeResource(getResources(),images[i]));
                i++;
                if(i==3){i=0;}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}