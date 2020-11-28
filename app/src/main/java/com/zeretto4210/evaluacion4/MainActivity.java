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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseClass connection;
    User loggedUser;

    TextView subtitle,count;
    ListView pkmn_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connection = new FirebaseClass(this);
        subtitle = (TextView) findViewById(R.id.m_subtitle);
        count = (TextView) findViewById(R.id.m_count);
        pkmn_list = (ListView) findViewById(R.id.m_pkmn_list);
        pkmn_list.setAdapter(connection.pokemonArrayAdapter);
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
                if (loggedUser != null){
                    toast("Ya iniciaste sesión");
                }
                else{
                    i = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivityForResult(i,20);
                }
                return true;
            }
            case R.id.menu_logout: {
                if (loggedUser != null){
                    logout();
                }
                else{
                    toast("No has iniciado sesión.");
                }
                return true;
            }
            case R.id.menu_pkmn_register: {
                if(loggedUser==null){
                    toast("Primero inicia sesión");
                }
                else{
                    i = new Intent(getApplicationContext(), PokemonRegister.class);
                    i.putExtra("user",loggedUser.getId());
                    startActivityForResult(i,30);
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
            try{
                registerUser(data);
                toast("Registro Correcto");
            } catch (Exception e){
                toast("No se pudo registrar usuario. (ex: "+e.getMessage()+")");
            }
        }
        if (requestCode == 20){ //Inicio de Sesión
            try{
                loginUser(data);
                toast("Iniciada la sesión");
            } catch (Exception e){
                toast("No se pudo iniciar sesión. (ex: "+e.getMessage()+")");
            }
        }
        if (requestCode == 30){ //Registro de Pokemón
            try{
                pokemonRegister(data);
                toast("Pokemón registrado");
            } catch (Exception e){
                toast("No se pudo registrar pokemón. (ex: "+e.getMessage()+")");
            }
        }
    }

    private void registerUser(Intent data){
        String i=data.getStringExtra("id");
        String u=data.getStringExtra("username");
        String p=data.getStringExtra("password");
        User newUser = new User(i,u,p);
        connection.reference.child("USERS").child(newUser.getId()).setValue(newUser);
    }

    private void loginUser(Intent data) {
        String user = data.getStringExtra("username");
        String password = data.getStringExtra("password");
        for (int i = 0;i<connection.userArrayAdapter.getCount();i++){
            User u = connection.userArrayAdapter.getItem(i);
            if(u.getUsername().equals(user) && u.getPassword().equals(password)){
                loggedUser=u;
            }
        }
        if(loggedUser != null){
            loadPkmns();
            subtitle.setText("Bienvenido, "+loggedUser.getUsername());
        }
        else{
            toast("Inicio de sesión Incorrecto.");
        }
    }

    private void pokemonRegister(Intent data){
        String i=data.getStringExtra("id");
        String n=data.getStringExtra("name");
        String u=data.getStringExtra("user");
        int h=data.getIntExtra("hp",0);
        int a=data.getIntExtra("atk",0);
        int d=data.getIntExtra("def",0);
        Pokemon pkmn = new Pokemon(i,n,u,h,a,d);
        connection.reference.child("PKMNs").child(pkmn.getId()).setValue(pkmn);
    }

    public void loadPkmns() {
        try{
            connection.reference.child("PKMNs").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    connection.pokemonList.clear();
                    for(DataSnapshot ds : snapshot.getChildren()){
                        Pokemon p = ds.getValue(Pokemon.class);
                        if(p.getUser_id().equals(loggedUser.getId())){
                            connection.pokemonList.add(p);
                            connection.pokemonArrayAdapter = new ArrayAdapter<>(connection.context,android.R.layout.simple_list_item_1,connection.pokemonList);
                            pkmn_list.setAdapter(connection.pokemonArrayAdapter);
                            connection.pokemonArrayAdapter.notifyDataSetChanged();
                            count.setText("Registros: "+connection.pokemonList.size());
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } catch (Exception e){

        }
    }
    private void logout() {
        loggedUser = null;
        subtitle.setText("Porfavor inicie sesión");
        count.setText("");
        connection.pokemonList.clear();
        connection.pokemonArrayAdapter.notifyDataSetChanged();
        toast("Se ha cerrado la sesión");
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