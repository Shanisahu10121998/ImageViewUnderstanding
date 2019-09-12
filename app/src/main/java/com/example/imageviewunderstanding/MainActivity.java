package com.example.imageviewunderstanding;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.print.PrintAttributes;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageView imageView;
ImageButton imageButton;
Intent intent;
final static int clickcode=1000;
Bitmap bitmap;
Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream inputStream=getResources().openRawResource(R.drawable.shani);
        bitmap= BitmapFactory.decodeStream(inputStream);
        imageButton=findViewById(R.id.imageButton1);
        imageView=findViewById(R.id.imageView1);
        button=findViewById(R.id.button1);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Camera Button", Toast.LENGTH_SHORT).show();
                intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,clickcode);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    getApplicationContext().setWallpaper(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //Press ctrl+o
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK)
        {
            Bundle bundle_shani=data.getExtras();
         bitmap= (Bitmap) bundle_shani.get("data");
         imageView.setImageBitmap(bitmap);

        }


    }
}
