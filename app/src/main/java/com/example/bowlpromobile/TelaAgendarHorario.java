package com.example.bowlpromobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TelaAgendarHorario extends AppCompatActivity {

    private Button bAgendar;
    private EditText nomeA, dataA, horaA;
    private ImageView voltarMenuCliente;
    private String[] msg = {"Preencha todos os campos!", "Horário agendado com sucesso!", "Você não pode mais agendar horários!"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendar_horario);
        allCompAgendarHorario();

        bAgendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome_a = nomeA.getText().toString();
                String data_a = dataA.getText().toString();
                String hora_a = horaA.getText().toString();
                if (nome_a.isEmpty() || data_a.isEmpty() || hora_a.isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, msg[0], Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                } else {
                    agendarHorario(v);
                }
            }
        });

        voltarMenuCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void agendarHorario(View v) {
        String nome = nomeA.getText().toString();
        String data = dataA.getText().toString();
        String hora = horaA.getText().toString();
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference horariosRef = db.collection("horarios");

        Map<String, Object> horario = new HashMap<>();
        horario.put("nome_cliente", nome);
        horario.put("data_horario", data);
        horario.put("horario", hora);
        horario.put("email_cliente", email);

        horariosRef.add(horario).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Snackbar snackbar = Snackbar.make(v, msg[1], Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
                nomeA.setText("");
                dataA.setText("");
                horaA.setText("");
                Log.d("db", "Sucesso ao agendar o horário!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("db_erro", "Erro ao agendar o horário!" + e.toString());
            }
        });
    }

    private void allCompAgendarHorario() {
        voltarMenuCliente = findViewById(R.id.iconVoltarforMenuCliente);
        nomeA = findViewById(R.id.nomeAgendar);
        dataA = findViewById(R.id.dataAgendar);
        horaA = findViewById(R.id.horaAgendar);
        bAgendar = findViewById(R.id.buttonAgendar);
    }
}
