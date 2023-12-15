package com.example.bowlpromobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bcliente,badm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allComponents();
        bcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TelaLoginCliente.class);
                startActivity(intent);
                finish();
            }
        });


    }
    private void allComponents(){
        bcliente = findViewById(R.id.buttonCliente);
        badm = findViewById(R.id.buttonADM);
    }

}