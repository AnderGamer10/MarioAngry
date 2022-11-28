package com.example.mijuego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isMute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        findViewById(R.id.idbtnJugar).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        });
        TextView highScoreTxt = findViewById(R.id.idHighScoreTxt);
        SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);

        highScoreTxt.setText("HighScore: " + prefs.getInt("highScore",0));
        isMute = prefs.getBoolean("isMute", false);
        final ImageView volumeCtrl = findViewById(R.id.btnMute);
        if (isMute){
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
        }else {
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);
        }
        volumeCtrl.setOnClickListener(view -> {
            isMute = !isMute;
            if (isMute){
                volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
            }else {
                volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);
            }
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("isMute", isMute);
            editor.apply();
        });
    }
}