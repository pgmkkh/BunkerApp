package com.tistory.pgmkkh.bunkerapp;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthvideo);

        String VIDEO_URL = getIntent().getStringExtra("DNS");
        Button finishBtn = (Button) findViewById(R.id.finishBtn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                videoView.stopPlayback();
                finish();
            }
        });

        videoView = (VideoView) findViewById(R.id.videoView);

        MediaController mc = new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse(VIDEO_URL));

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer player) {
                videoView.seekTo(0);
                videoView.start();
            }
        });

    }
}