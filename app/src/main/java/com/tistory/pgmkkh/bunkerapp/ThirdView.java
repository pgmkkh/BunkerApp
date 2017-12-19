package com.tistory.pgmkkh.bunkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Pgmkkh on 2017-12-20.
 */

public class ThirdView extends AppCompatActivity{
    static MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thirdview);

        // 데이터 원본 준비
        ArrayList<Safe> data = new ArrayList<Safe>();
        data.add(new Safe(R.drawable.army, "기무사", "간첩신고, 군사기밀","tel:01051706344", "http://www.dsc.mil.kr/main.do?cmd=main"));
        data.add(new Safe(R.drawable.fire, "119 안전신고센터", "화재,구조,구급,재난신고","tel:01042221859", "http://www.119.go.kr/Center119/main.do"));
        data.add(new Safe(R.drawable.pol, "경찰청", "범죄신고","tel:01064773573","https://minwon.police.go.kr/"));
        data.add(new Safe(R.drawable.fem, "여성긴급전화", "성관련범죄","tel:1399","http://www.mogef.go.kr/cc/wcc/cc_wcc_f001.do"));
        data.add(new Safe(R.drawable.cyber, "인터넷진흥원", "사이버테러","tel:118","https://www.boho.or.kr/main.do"));
        data.add(new Safe(R.drawable.dis, "질병관리본부", "전염병,감염 신고","tel:1339","http://www.cdc.go.kr/CDC/main.jsp"));
        data.add(new Safe(R.drawable.safe, "행정안전부", "재난신고","tel:0221003399","http://www.mois.go.kr/frt/a03/civilComplaintHome.do"));
        data.add(new Safe(R.drawable.gas, "가스안전공사", "가스사고","tel:15444500","http://www.kgs.or.kr/kgsmain/index.do"));


        adapter = new MyAdapter(this, R.layout.item_listview2, data);
        ListView listView = (ListView)findViewById(R.id.listView11);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {
                Intent intent = new Intent(ThirdView.this, PopupActivity.class);
                intent.putExtra("pic", ((Safe)adapter.getItem(position)).mIcon);
                intent.putExtra("name", ((Safe)adapter.getItem(position)).nName);
                intent.putExtra("hint", ((Safe)adapter.getItem(position)).nhint);
                intent.putExtra("call",((Safe)adapter.getItem(position)).nTel);
                intent.putExtra("DNS",((Safe)adapter.getItem(position)).nDns);
                startActivityForResult(intent, 1);

            }
        });


    }

}
