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
import com.example.appga.Model.Categoria_Leitor;
import com.example.appga.R;
import java.util.ArrayList;


public class Cad_CategoriaLeitor extends AppCompatActivity {

    ListView lista;
    LivrariaBD banco;
    ArrayList<Categoria_Leitor> listview_Categoria;
    Categoria_Leitor categoria;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad__categoria_leitor);

        Button btnCadastrar = (Button) findViewById(R.id.btn_CadastroFormLeitor);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Cad_CategoriaLeitor.this, Formulario_CatLeitores.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listview_CategoriaLeitor);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Categoria_Leitor categoriaEscolhida = (Categoria_Leitor) adapter.getItemAtPosition(position);

                Intent it = new Intent(Cad_CategoriaLeitor.this, Formulario_CatLeitores.class);
                it.putExtra("categoriaLeitor",categoriaEscolhida);
                startActivity(it);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
                categoria = (Categoria_Leitor) adapter.getItemAtPosition(position);
                return false;
            }
        });


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar esta Categoria");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                banco = new LivrariaBD (Cad_CategoriaLeitor.this);
                banco.deletarCategoriaLeitor(categoria);
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
        banco = new LivrariaBD(Cad_CategoriaLeitor.this);
        listview_Categoria = banco.getLista_CatLeitor();
        banco.close();

        if(listview_Categoria != null){
            adapter = new ArrayAdapter<Categoria_Leitor>(Cad_CategoriaLeitor.this, android.R.layout.simple_list_item_1,listview_Categoria);
            lista.setAdapter(adapter);
        }


    }
}
