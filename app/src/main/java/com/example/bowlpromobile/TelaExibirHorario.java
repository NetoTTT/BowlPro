package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaExibirHorario extends AppCompatActivity {

    private ImageView voltarMenu2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exibir_horario);
        allCompExibirHorario();

        voltarMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void allCompExibirHorario(){
        voltarMenu2 = findViewById(R.id.iconVoltarforMenuCliente2);
    }
}
