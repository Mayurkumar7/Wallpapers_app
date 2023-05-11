package com.example.wallpapersapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.IOException;

public class wallpaper_activity extends AppCompatActivity {
private ImageView wallpaperIv;
private Button setWallpaperBtn;
private  String imgUrl;
WallpaperManager wallpaperManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
wallpaperIv = findViewById(R.id.idIvWallpapersS);
setWallpaperBtn = findViewById(R.id.idBtnSetWallpaper);
imgUrl = getIntent().getStringExtra("imgUrl");
        Glide.with(this).load(imgUrl).into(wallpaperIv);

        wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        setWallpaperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(wallpaper_activity.this).asBitmap().load(imgUrl).listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        Toast.makeText(wallpaper_activity.this, "fail to load image..", Toast.LENGTH_SHORT).show();
                      return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
try {
    wallpaperManager.setBitmap(resource);

}catch (IOException e){
    e.printStackTrace();
    Toast.makeText(wallpaper_activity.this, "fail to set wallpaper", Toast.LENGTH_SHORT).show();
}

                        return false;
                    }
                }).submit();

            }
        });




    }
}