package com.example.agenda;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        final Context mContext = this;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
            }
        }, 500);

    }

    public void onClickContinue(View view){
        //para ir al main al apretar una imagen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
