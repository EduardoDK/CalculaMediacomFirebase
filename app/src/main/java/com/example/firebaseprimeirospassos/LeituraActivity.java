package com.example.firebaseprimeirospassos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LeituraActivity extends AppCompatActivity {


    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView textViewLeitura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leitura);

        textViewLeitura = findViewById(R.id.text_view_leitura);

        conectarBanco();


        //Listener executa a cada momento que se tem alteração no banco

        databaseReference.child("Dicionario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               Double nota1 = Double.parseDouble(dataSnapshot.child("Valor1").child("Valor").getValue().toString().replace(",","."));
               Double nota2 = Double.parseDouble(dataSnapshot.child("Valor2").child("Valor").getValue().toString().replace(",","."));
               Double nota3 = Double.parseDouble(dataSnapshot.child("Valor3").child("Valor").getValue().toString().replace(",","."));



                Double media = (nota1+nota2+nota3)/3;



                String mediaString = String.format("%.2f",media.doubleValue());


                textViewLeitura.setText( "A média foi  "  + mediaString.toString());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void conectarBanco(){

        FirebaseApp.initializeApp(LeituraActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


}
