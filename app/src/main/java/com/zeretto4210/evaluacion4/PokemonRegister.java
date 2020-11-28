package com.zeretto4210.evaluacion4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class PokemonRegister extends AppCompatActivity {
    TextView name, hp, atk, def;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_register);
        name = (TextView) findViewById(R.id.pr_name);
        hp = (TextView) findViewById(R.id.pr_hp);
        atk = (TextView) findViewById(R.id.pr_atk);
        def = (TextView) findViewById(R.id.pr_def);
    }
    public void registerPokemon(View view){
        String sName, sID, sUser;
        int sHP, sATK, sDEF;
        sName = ""+name.getText();
        sID = UUID.randomUUID().toString();
        sUser = getIntent().getStringExtra("user");
        sHP = Integer.parseInt(""+hp.getText());
        sATK = Integer.parseInt(""+atk.getText());
        sDEF = Integer.parseInt(""+def.getText());
        Intent i = new Intent();
        i.putExtra("id", sID);
        i.putExtra("name",sName);
        i.putExtra("user",sUser);
        i.putExtra("hp",sHP);
        i.putExtra("atk",sATK);
        i.putExtra("def",sDEF);
        setResult(30,i);
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