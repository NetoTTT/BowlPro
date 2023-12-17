package com.example.bowlpromobile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class TelaMenu_ADM extends AppCompatActivity {
    private Button bBuscarCliente,bBuscarFun,bCadastrarFun;
    private TextView cAgenda,cCliente;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_adm);
        allCompMenuADM();

        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef1 = db.collection("ADM").document(userID);
        CollectionReference agendasRef1 = userRef1.collection("Agendas");
        agendasRef1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot documentSnapshot = task.getResult();

                    int quantidadeDocumentos = documentSnapshot.size();
                    cAgenda.setText(quantidadeDocumentos);

                }
            }
        });
    }

    private void allCompMenuADM(){
        bBuscarCliente = findViewById(R.id.buttonBuscarCliente);
        bBuscarFun = findViewById(R.id.buttonBuscarFun);
        bCadastrarFun = findViewById(R.id.buttonCadastrarFun);
        cAgenda = findViewById(R.id.constant_agendas_Num);
        cCliente = findViewById(R.id.constant_cliente_Num);
    }
}
