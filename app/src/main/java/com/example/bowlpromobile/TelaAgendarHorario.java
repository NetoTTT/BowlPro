package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaAgendarHorario extends AppCompatActivity {

    Button bl3;
    ImageView voltarMenuCliente;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agendar_horario);
        allCompAgendarHorario();
        bl3.setVisibility(View.INVISIBLE);
        voltarMenuCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void allCompAgendarHorario(){
        voltarMenuCliente = findViewById(R.id.iconVoltarforMenuCliente);
        bl3 = findViewById(R.id.bl3);
    }
}
