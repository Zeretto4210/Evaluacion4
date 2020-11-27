package com.zeretto4210.evaluacion4;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    User user;
    FirebaseClass firebaseConnection;
    ArrayList<User> userList;
    ArrayList<Pokemon> pokemonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseConnection = new FirebaseClass(this);
        userList= new ArrayList<>();
        toast(userList.size()+"");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        switch(item.getItemId()){
            case R.id.menu_login:{
                if (user != null){
                    toast("Ya iniciaste sesión");
                }
                else{
                    i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivityForResult(i,20);
                }
                return true;
            }
            case R.id.menu_logout: {
                if (user != null){
                    logout();
                }
                else{
                    toast("No has iniciado sesión.");
                }
                return true;
            }
            case R.id.menu_pkmn_register: {
                if(user==null){
                    toast("Primero inicia sesión");
                }
                else{
                    i = new Intent(getApplicationContext(), PokemonRegister.class);
                    startActivity(i);
                }
                return true;
            }
            case R.id.menu_user_register: {
                i = new Intent(getApplicationContext(), UserRegister.class);
                startActivityForResult(i,10);
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10){ //Registro de usuario
            String i=data.getStringExtra("id"),u=data.getStringExtra("username"),p=data.getStringExtra("password");
            User newUser = new User(i,u,p);
            firebaseConnection.reference.child("Usuarios").child(newUser.getId()).setValue(newUser);
            toast("Registro Correcto");
        }
        if (requestCode == 20){ //Inicio de Sesión
            String i = data.getStringExtra("id");
            firebaseConnection.reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot u : snapshot.getChildren()){
                        if (u.getKey().equals(i)){
                            user = u.getValue(User.class);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void logout() {
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