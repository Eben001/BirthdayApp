package com.ebenezer.gana.birthdayapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer player;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        player = MediaPlayer.create(getApplicationContext(),R.raw.birthdaytune );
        player.setLooping(true);
        player.start();

        AudioManager audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(
                AudioManager.STREAM_MUSIC /2), 0);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
