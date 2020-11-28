package com.zeretto4210.evaluacion4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {
    TextView username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.lg_name);
        password = (TextView) findViewById(R.id.lg_password);
    }
    public void loginButton(View view){
        String sUsername, sPassword;
        sUsername = ""+username.getText();
        sPassword = ""+password.getText();
        Intent i = new Intent();
        i.putExtra("username",sUsername);
        i.putExtra("password",sPassword);
        setResult(20,i);
        finish();
    }
    //<editor-fold defaultstate="collapsed" desc="Aquí hay un generador de toast, na que hacer">
    public void toast(String text){
        android.widget.Toast.makeText(getApplicationContext(),text, Toast.LENGTH_SHORT).show();
    }
    public void toast(String text, boolean longTime){
        android.widget.Toast.makeText(getApplicationContext(),text,longTime ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
    //</editor-fold>
}