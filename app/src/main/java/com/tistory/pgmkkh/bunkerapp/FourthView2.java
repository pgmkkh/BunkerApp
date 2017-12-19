package com.tistory.pgmkkh.bunkerapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class FourthView2 extends AppCompatActivity {
    private VideoAdapter adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourthview2);

        ArrayList<IVideo> data = new ArrayList<IVideo>();
        data.add(new IVideo(R.drawable.movie,"전시 행동요령","Youtube","https://www.youtube.com/watch?v=etmLyWprJUk"));
        data.add(new IVideo(R.drawable.movie,"전시 생존물품","Youtube","https://www.youtube.com/watch?v=hdh8Kpo7z_k"));
        data.add(new IVideo(R.drawable.movie,"민방공 대피요령","국민안전처","https://www.youtube.com/watch?v=Uv96by7SLS4"));
        data.add(new IVideo(R.drawable.movie,"핵무기 공격시 행동요령","국민안전처","https://www.youtube.com/watch?v=K5XqWEAjF4o"));

        adapter1 = new VideoAdapter(this, R.layout.itemlistview3, data);
        ListView listView = (ListView)findViewById(R.id.listView111);
        listView.setAdapter(adapter1);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
              //  String name = ((IVideo)adapter.getItem(position)).nName;
                Intent intent = new Intent(FourthView2.this,VideoActivity.class);
              intent.putExtra("DNS", (((IVideo) adapter1.getItem(position)).nDns1));
               startActivityForResult(intent, 11111);
            }
        });
    }

}