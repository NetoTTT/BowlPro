package com.example.bowlpromobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bowlpromobile.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TelaExibirHorario extends AppCompatActivity {

    private ImageView voltarMenu2;
    private RecyclerView recyclerView;
    private HorarioAdapter adapter;
    private List<Horario> horarios = new ArrayList<>();
    private Button bCancelarH;
    String msg[] = {"Horário(s) cancelado(s) com sucesso!", "Erro ao cancelar o(s) Horário(s)!"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String emailCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_horario);
        allCompExibirHorario();

        // Obter o email do usuário autenticado
        emailCliente = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HorarioAdapter(horarios);
        recyclerView.setAdapter(adapter);

        // Buscar horários do usuário
        fetchHorarios();

        voltarMenu2.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
            startActivity(i);
            finish();
        });

        bCancelarH.setOnClickListener(v -> {
            Set<Integer> selectedPositions = adapter.getSelectedItems(); // Obtém os itens selecionados

            if (selectedPositions.isEmpty()) {
                Snackbar snackbar = Snackbar.make(v, "Nenhum horário selecionado.", Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(Color.WHITE);
                snackbar.setTextColor(Color.BLACK);
                snackbar.show();
                return; // Sai da função se nenhum item foi selecionado
            }

            // Converta o Set em uma lista e ordene em ordem decrescente
            List<Integer> sortedPositions = new ArrayList<>(selectedPositions);
            sortedPositions.sort((a, b) -> b - a);

            for (int position : sortedPositions) {
                Horario horario = horarios.get(position); // Pega o horário com base na posição
                String id = horario.getId(); // Certifique-se de que o ID está presente

                if (id != null && !id.isEmpty()) {
                    DocumentReference docRef = db.collection("horarios").document(id);
                    docRef.delete().addOnSuccessListener(aVoid -> {
                        Log.d("Firestore", "Horário excluído com sucesso.");
                    }).addOnFailureListener(e -> {
                        Log.w("Firestore", "Erro ao excluir horário.", e);
                    });
                } else {
                    Log.w("Firestore", "ID do documento está nulo ou vazio.");
                }

                horarios.remove(position); // Remove o item da lista de trás para frente
            }

            adapter.notifyDataSetChanged(); // Atualiza a lista
        });




    }

    private void fetchHorarios() {
        CollectionReference horariosRef = db.collection("horarios");
        horariosRef.whereEqualTo("email_cliente", emailCliente).addSnapshotListener((querySnapshot, error) -> {
            if (querySnapshot != null) {
                horarios.clear(); // Limpa a lista antes de adicionar novos itens
                for (DocumentSnapshot document : querySnapshot.getDocuments()) {
                    Horario horario = document.toObject(Horario.class);
                    if (horario != null) {
                        horario.setId(document.getId()); // Define o ID do documento no objeto Horario
                        horarios.add(horario);
                    }
                }
                adapter.notifyDataSetChanged();
            } else if (error != null) {
                Log.e("Firestore", "Erro ao buscar horários", error);
            }
        });
    }


    private void deleteHorarioFromDatabase(Horario horario) {
        db.collection("horarios").document(horario.getId()).delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d("Firestore", "Horário excluído com sucesso.");
            } else {
                Log.w("Firestore", "Erro ao excluir horário.", task.getException());
            }
        });
    }

    private void allCompExibirHorario() {
        voltarMenu2 = findViewById(R.id.iconVoltarforMenuCliente2);
        recyclerView = findViewById(R.id.recyclerViewHorarios);
        bCancelarH = findViewById(R.id.cancelarHorario);
    }
}
