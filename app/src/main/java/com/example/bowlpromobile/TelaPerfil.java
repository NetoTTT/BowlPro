package com.example.bowlpromobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class TelaPerfil extends AppCompatActivity {

    private ImageView voltaMenuCliente3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
        allCompTelaPerfil();

        voltaMenuCliente3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Menu_Cliente.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void allCompTelaPerfil(){
        voltaMenuCliente3 = findViewById(R.id.iconVoltarforMenuCliente3);
    }
}
