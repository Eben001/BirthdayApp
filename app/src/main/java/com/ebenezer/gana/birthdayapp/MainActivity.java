package com.ebenezer.gana.birthdayapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class MainActivity extends AppCompatActivity {

    KonfettiView konfettiView;
    TextView tvBirthdayMsg, tvBirthdayWishes;
    CircleImageView ivBig, ivLeft, ivRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivBig = findViewById(R.id.ivBig);
        ivLeft = findViewById(R.id.ivLeft);
        ivRight = findViewById(R.id.ivRight);


        tvBirthdayMsg = findViewById(R.id.tvBirthdayMsg);
        tvBirthdayWishes = findViewById(R.id.tvBirthdayWishes);
        konfettiView = findViewById(R.id.viewKonfetti);

        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.pic_anim);
        ivBig.setAnimation(rotation);

        delayedBirthdayText();
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(340.0, 359.0)
                .setSpeed(1f, 2f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 50000000L);


    }

    private void delayedBirthdayText() {

        //not advisable to run all these on the main thread

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvBirthdayWishes.setText(getResources().getText(R.string.birthdaymsg));
            }
        }, 10000);  // 10000 = 10 seconds

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvBirthdayWishes.setText(getResources().getText(R.string.bdayWish3));
            }
        }, 18000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvBirthdayWishes.setText(getResources().getText(R.string.bdayWish4));
            }
        }, 25000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvBirthdayWishes.setText(getResources().getText(R.string.bdayWish5));
            }
        }, 35000);

    }


    @Override
    protected void onStart() {
        startService(new Intent(this, MusicService.class));
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        stopService(new Intent(this, MusicService.class));
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        stopService(new Intent(this, MusicService.class));

        super.onPause();
    }
}