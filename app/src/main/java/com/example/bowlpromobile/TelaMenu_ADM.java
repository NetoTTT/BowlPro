package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TelaMenu_ADM extends AppCompatActivity {
    private Button bCadastrarFun,bIn;
    private TextView cCliente;

    private ImageView voltarL;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_adm);
        allCompMenuADM();

        bIn.setVisibility(View.INVISIBLE);
        voltarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference agendasRef1 = db.collection("Clientes");
        agendasRef1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();

                    // Obtém o número de documentos na coleção
                    int quantidadeDocumentos = querySnapshot.size();
                    String quantidadeDocumentos1 = String.valueOf(quantidadeDocumentos);
                    cCliente.setText(quantidadeDocumentos1);
                    // Faça algo com a quantidade de documentos
                    Log.d("Firestore", "Quantidade de documentos: " + quantidadeDocumentos);
                } else {
                    // Tratar erro
                    Log.e("Firestore", "Erro ao obter documentos", task.getException());
                }
            }
        });
    }

    private void allCompMenuADM(){
        bCadastrarFun = findViewById(R.id.buttonCadastrarFun);
        cCliente = findViewById(R.id.constant_cliente_Num);
        voltarL = findViewById(R.id.iconVoltarforLogin4);
        bIn= findViewById(R.id.bl22);
    }
}
