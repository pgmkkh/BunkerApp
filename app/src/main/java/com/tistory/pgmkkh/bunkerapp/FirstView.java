package com.tistory.pgmkkh.bunkerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class FirstView extends Activity {
    private ListView lvProduct;
    private ListProductAdapter adapter;
    private List<Product> mProductList;
    private DatabaseHelper mDBHelper;
    private int ii;
    ArrayAdapter<CharSequence> bigCity, smallCity; // big -> seoul..  small -> 강서구..

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.firstview);
        lvProduct = (ListView) findViewById(R.id.listview_product);
        mDBHelper = new DatabaseHelper(this);
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        bigCity = ArrayAdapter.createFromResource(this, R.array.city_list, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(bigCity);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if (copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//첫번째 spinner 클릭시 이벤트 발생입니다. setO 정도까지 치시면 자동완성됩니다. 뒤에도 마찬가지입니다.
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {//제대로 자동완성하셨다면 이부분이 자동으로 만들어 질 것입니다. int i는 포지션이라 하여 제가 spinner에 몇번째걸 선택했는지 값이 들어갑니다. 필요하겠죠? ㅎㅎ
                ii = i;
                if (bigCity.getItem(i).equals("서울특별시")) {

                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.seoul_list, android.R.layout.simple_spinner_dropdown_item);//서울일 경우에 두번째 spinner에 값을 넣습니다. //그냥 this가 아닌 Main~~~인 이유는 그냥 this는 메인엑티비티인 경우만 가능합니다. //지금과 같이 다른 함수안이나 다른 클래스에서는 꼭 자신의 것을 넣어주어야 합니다.//혹시나 다른 class -> Public View밑에서 작업하시는 분은 view명.getContext()로 해주셔야 합니다.//예로 View rootView =~~ 선언하신 경우에는 rootView.getContext()써주셔야합니다. this가 아니라요.
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);//두번째 어댑터값을 두번째 spinner에 넣었습니다.
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {//저희는 두번째 선택된 값도 필요하니 이안에 두번째 spinner 선택 이벤트를 정의합니다.
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            // choice_se = adspin2.getItem(i).toString();//두번째 선택된 값을 choice_se에 넣습니다.
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {//아무것도 선택안될시 부분입니다. 자동완성됩니다.
                        }
                    });
                } else if (bigCity.getItem(i).equals("인천광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.incheon_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            // choice_se = adspin2.getItem(i).toString();
                            Log.d("asd","Big");
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("경기도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.gyeonggi_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("강원도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.kangwon_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("경상남도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.gNam_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("경상북도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.gbuk_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("광주광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.gwangju_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("대구광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.daegu_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("대전광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.daejeon_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("부산광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.busan_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("울산광역시")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.ulsan_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("전라남도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.jNam_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("전라북도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.jBuk_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }
                else if (bigCity.getItem(i).equals("제주도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.jeju_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if (bigCity.getItem(i).equals("충청남도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.cNam_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }else if (bigCity.getItem(i).equals("충청북도")) {//똑같은 소스에 반복입니다. 인천부분입니다.
                    smallCity = ArrayAdapter.createFromResource(FirstView.this, R.array.cBuk_list, android.R.layout.simple_spinner_dropdown_item);
                    smallCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner2.setAdapter(smallCity);
                    spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
                            mProductList = mDBHelper.getListProduct(bigCity.getItem(ii),smallCity.getItem(j));
                            adapter = new ListProductAdapter(getApplicationContext(), mProductList);
                            lvProduct.setAdapter(adapter);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        ///////////////////////////////////////////////////////////////////////////////
        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //클릭 이벤트 뷰, 실제 Adapter 뷰, 클릭 위치, 항목 id
            public void onItemClick(AdapterView<?> parent, View vClicked,
                                    int position, long id) {

                String bName = ((Product) adapter.getItem(position)).getbName();
                String loc = ((Product) adapter.getItem(position)).getLoc();
                double lati = ((Product) adapter.getItem(position)).getLati();
                double longi = ((Product) adapter.getItem(position)).getLongi();
                String capa = ((Product) adapter.getItem(position)).getCapa();
                String phone = ((Product) adapter.getItem(position)).getPhone();
                String aName = (((Product) adapter.getItem(position)).getaName());


                Intent intent = new Intent(getApplicationContext(), Info.class);
                intent.putExtra("bName", bName);
                intent.putExtra("loc", loc);
                intent.putExtra("lati", lati);
                intent.putExtra("longi", longi);
                intent.putExtra("capa", capa);
                intent.putExtra("phone", phone);
                intent.putExtra("aName", aName);

                startActivityForResult(intent, 999999);
            }
        });
        /////////////////////////////////////////////////////////////////////////////////
    }

    private boolean copyDatabase(Context context) {
        try {

            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}