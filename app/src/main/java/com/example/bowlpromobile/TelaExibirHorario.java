package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TelaExibirHorario extends AppCompatActivity {

    private ImageView voltarMenu2;
    private TextView old_Exibir,hora_Exibir;
    private Button bCancelarH;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_horario);
        allCompExibirHorario();



        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String nomeArquivo = userID;
        String b = lerArquivo(nomeArquivo);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference userRef1 = db.collection("Clientes").document(userID);
        CollectionReference agendasRef1 = userRef1.collection("Agendas");
        DocumentReference agendaDocRef = agendasRef1.document(b);
        agendaDocRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {

                if(documentSnapshot != null){
                    old_Exibir.setText(documentSnapshot.getString("Data"));
                    hora_Exibir.setText(documentSnapshot.getString("Hora"));
                }
            }
        });

        voltarMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
                startActivity(i);
                finish();
            }
        });

    }

    public static String lerArquivo(String nomeArquivo) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conteudo.toString();
    }


    private void allCompExibirHorario(){
        voltarMenu2 = findViewById(R.id.iconVoltarforMenuCliente2);
        old_Exibir = findViewById(R.id.caixa_text_old_HM);
        hora_Exibir = findViewById(R.id.caixa_text_hora_HM);
        bCancelarH = findViewById(R.id.cancelarHorario);
    }
}
