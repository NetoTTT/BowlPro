package com.example.bowlpromobile;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class TelaCod_Only extends AppCompatActivity {

    private Button entrarADM;
    private EditText digitarCOD;

    private String msg = "Senha incorreta";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cod_only);
        allCompLoginADM();

        entrarADM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String senha = digitarCOD.getText().toString();

                if(senha.equals("admin")){
                    digitarCOD.setText("");
                    Intent i = new Intent(getApplicationContext(),TelaMenu_ADM.class);
                    startActivity(i);
                    finish();
                }else {
                    Snackbar snackbar = Snackbar.make(v, msg, Snackbar.LENGTH_SHORT);
                    snackbar.setBackgroundTint(Color.WHITE);
                    snackbar.setTextColor(Color.BLACK);
                    snackbar.show();
                }
            }
        });

    }

    private void allCompLoginADM(){
        entrarADM = findViewById(R.id.buttonEntrarADM);
        digitarCOD = findViewById(R.id.email_edit_login3);
    }
}
