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
    FirebaseClass firebaseConnection;
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.lg_name);
        password = (TextView) findViewById(R.id.lg_password);
        firebaseConnection = new FirebaseClass(this);
        userList = firebaseConnection.loadUsers();
        toast(userList.get(0).getUsername());
    }
    public void loginButton(View view){
        String sUsername, sPassword;
        sUsername = ""+username.getText();
        sPassword = ""+password.getText();
        boolean found = false;
        for(User u : userList){
            if (u.username.equals(sUsername)){
                found = true;
                if (u.password.equals(sPassword)){
                    Intent i = new Intent();
                    i.putExtra("id", u.id);
                    setResult(20,i);
                    finish();
                }
                else{
                    toast("Contraseña Incorrecta");
                    break;
                }
            }
        }
        if (!found){
            toast("Usuario no encontrado");
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