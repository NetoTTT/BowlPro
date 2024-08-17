package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class BuscarFun extends AppCompatActivity {

    private Button bBuscarFun;
    private TextView tNome, tCPF, tIdade, tCargo;
    private ImageView voltarMenuADM222;
    private EditText tId;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar_fun);
        AllCompBuscarFun();

        voltarMenuADM222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), TelaMenu_ADM.class);
                startActivity(i);
                finish();
            }
        });

        bBuscarFun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String buscar = tId.getText().toString();
                if (buscar.isEmpty()) {
                    Toast.makeText(BuscarFun.this, "Digite um ID válido", Toast.LENGTH_SHORT).show();
                    return;
                }

                DocumentReference d = db.collection("funcionarios").document(buscar);
                d.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                tNome.setText(document.getString("nome"));
                                tCPF.setText(document.getString("cpf"));
                                tIdade.setText(document.getString("idade"));
                                tCargo.setText(document.getString("cargo"));
                            } else {
                                // Documento não encontrado
                                tNome.setText("Não encontrado");
                                tCPF.setText("Não encontrado");
                                tIdade.setText("Não encontrado");
                                tCargo.setText("Não encontrado");
                            }
                        } else {
                            // Erro ao buscar o documento
                            Toast.makeText(BuscarFun.this, "Erro ao buscar documento: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void AllCompBuscarFun() {
        bBuscarFun = findViewById(R.id.buttonBuscarFun3);
        tNome = findViewById(R.id.Caixa_text_nome_perfil2);
        tCPF = findViewById(R.id.caixa_text_email_perfil22);
        tIdade = findViewById(R.id.caixa_text_old_perfil2);
        tCargo = findViewById(R.id.caixa_text_email_perfil2);
        tId = findViewById(R.id.id_fun2);
        voltarMenuADM222 = findViewById(R.id.iconVoltarforMenuCliente333);
    }
}
