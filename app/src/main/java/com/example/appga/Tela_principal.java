package com.example.appga;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.appga.Cadastro.Tela_cadastro;


public class Tela_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        Button cadastro = (Button) findViewById(R.id.btnCadastro);
        Button consulta = (Button) findViewById(R.id.btnConsulta);

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_principal.this, Tela_cadastro.class);
                startActivity(it);
            }
        });

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_principal.this, Tela_Consulta.class);
                startActivity(it);
            }
        });

    }
}
