package com.zeretto4210.evaluacion4;

import android.app.Application;
import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseClass {
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;
    public FirebaseClass(Context context){
        this.context = context;
        this.initFirebase();
    }
    public void initFirebase(){
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        //database.setPersistenceEnabled(true);
        reference = database.getReference();
    }
    public ArrayList<User> loadUsers() {
        reference.child("Usuarios")
        ArrayList<User> userList = new ArrayList<>();
        reference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userList.clear();
                for(DataSnapshot ds : snapshot.getChildren()){
                    User p = ds.getValue(User.class);
                    userList.add(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return userList;
    }
}
