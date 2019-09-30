package com.example.appga.Cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.appga.Banco.LivrariaBD;
import com.example.appga.Model.Leitor;
import com.example.appga.Model.Livro;
import com.example.appga.R;

import java.util.ArrayList;

public class Cad_Livros extends AppCompatActivity {

    ListView lista;
    LivrariaBD banco;
    ArrayList<Livro> listview_Livro;
    Livro livro;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad__livros);


        Button btnCadastrar = (Button) findViewById(R.id.btn_CadastroLivro);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Cad_Livros.this, Formulario_Livros.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Livro);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Livro livroEscolhido = (Livro) adapter.getItemAtPosition(position);

                Intent it = new Intent(Cad_Livros.this, Formulario_Livros.class);
                it.putExtra("Livro",livroEscolhido);
                startActivity(it);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
                livro = (Livro) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este Livro");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                banco = new LivrariaBD (Cad_Livros.this);
                banco.deletarLivro(livro);
                banco.close();
                carregarLista();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    public void carregarLista(){
        banco = new LivrariaBD(Cad_Livros.this);
        listview_Livro = banco.getLista_Livro();
        banco.close();

        if(listview_Livro != null){
            adapter = new ArrayAdapter<Livro>(Cad_Livros.this, android.R.layout.simple_list_item_1,listview_Livro);
            lista.setAdapter(adapter);
        }


    }
}
