package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

import com.example.appga.R;


public class Tela_cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tela_cadastro);
        Button cliente = (Button) findViewById(R.id.btnCliente);
        Button catLeitores = (Button) findViewById(R.id.btnCatLeitores);
        Button catLivros = (Button) findViewById(R.id.btnCatLivros);
        Button livros = (Button) findViewById(R.id.btnLivros);

        cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_cadastro.this, Cad_Leitor.class);
                startActivity(it);
            }
        });

        catLeitores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_cadastro.this, Cad_CategoriaLeitor.class);
                startActivity(it);
            }
        });

        catLivros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_cadastro.this, Cad_CategoriaLivro.class);
                startActivity(it);
            }
        });

        livros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Tela_cadastro.this, Cad_Livros.class);
                startActivity(it);
            }
        });

    }


}
