package com.zeretto4210.evaluacion4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class UserRegister extends AppCompatActivity {

    TextView username, password, password2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        username = (TextView) findViewById(R.id.ur_name);
        password = (TextView) findViewById(R.id.ur_password);
        password2 = (TextView) findViewById(R.id.ur_password2);
    }
    public void registerUser(View view){
        String sUsername, sPassword, sPassword2;
        sUsername = ""+username.getText();
        sPassword = ""+password.getText();
        sPassword2 = ""+password2.getText();
        if (!sPassword.equals(sPassword2)){
            toast("Sus contraseñas no coinciden");
        } else{
            Intent i = new Intent();
            i.putExtra("id",UUID.randomUUID().toString());
            i.putExtra("username",sUsername);
            i.putExtra("password",sPassword);
            setResult(10,i);
            finish();
        }
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