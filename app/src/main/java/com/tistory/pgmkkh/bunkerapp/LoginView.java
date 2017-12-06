package com.tistory.pgmkkh.bunkerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Kyungho on 2017-12-06.
 */

public class LoginView extends AppCompatActivity {

    public static final String PREFERENCES_GROUP = "LoginInfo";
    public static final String PREFERENCES_ATTR1 = "Username";
    public static final String PREFERENCES_ATTR2 = "Password";
    public static final String USER_NAME="hello";
    public static final String PASSWORD="1234";
    SharedPreferences setting;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //loginInfo라는 프레퍼런스 등록
        setting = getSharedPreferences(PREFERENCES_GROUP, MODE_PRIVATE);
        //입력 상자에 값 세팅
        final EditText textInput1 = (EditText) findViewById(R.id.nametext);
        final EditText textInput2 = (EditText) findViewById(R.id.passtext);

        textInput1.setText(retrieveName());
        textInput2.setText(retrievePassword());

        Button btn = (Button) findViewById(R.id.loginbutton);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name = textInput1.getText().toString();
                String password = textInput2.getText().toString();

                saveName(name);
                savePassword(password);

                if (name.equals(USER_NAME) && password.equals(PASSWORD)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(LoginView.this, "Login Failed (hello / 1234)", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private String retrieveName() {
        String nameText = "";
        if (setting.contains(PREFERENCES_ATTR1)) {
            nameText = setting.getString(PREFERENCES_ATTR1, "");
        }
        return nameText;
    }

    private String retrievePassword() {
        String pwText = "";
        if (setting.contains(PREFERENCES_ATTR2)) {
            pwText = setting.getString(PREFERENCES_ATTR2, "");
        }
        return pwText;
    }

    private void saveName(String text) {
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(PREFERENCES_ATTR1, text);
        editor.commit();
    }
    private void savePassword(String text) {
        SharedPreferences.Editor editor = setting.edit();
        editor.putString(PREFERENCES_ATTR2, text);
        editor.commit();
    }
}
