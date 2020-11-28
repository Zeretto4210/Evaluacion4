package com.zeretto4210.evaluacion4;

import android.app.Application;
import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseClass {
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;

    List<User> userList;
    ArrayAdapter<User> userArrayAdapter;
    ListView userListView;

    List<Pokemon> pokemonList;
    ArrayAdapter<Pokemon> pokemonArrayAdapter;
    ListView pokemonListView;


    public FirebaseClass(Context context){
        this.context = context;

        userList = new ArrayList<>();
        userListView = new ListView(context);
        userListView.setVisibility(View.INVISIBLE);

        pokemonList = new ArrayList<>();
        pokemonListView = new ListView(context);
        pokemonListView.setVisibility(View.INVISIBLE);

        this.initFirebase();
        this.loadUsers();
    }
    public void initFirebase(){
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public void loadUsers() {
        reference.child("USERS").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    User u = ds.getValue(User.class);
                    userList.add(u);
                    userArrayAdapter = new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,userList);
                    userListView.setAdapter(userArrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
