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
import com.example.appga.R;

import java.util.ArrayList;

public class Cad_Leitor extends AppCompatActivity {

    ListView lista;
    LivrariaBD banco;
    ArrayList<Leitor> listview_Leitor;
    Leitor leitor;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad__leitor);

        Button btnCadastrar = (Button) findViewById(R.id.btn_CadastroLeitor);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Cad_Leitor.this, Formulario_Leitores.class);
                startActivity(it);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Leitor);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

                Leitor leitorEscolhido = (Leitor) adapter.getItemAtPosition(position);

                Intent it = new Intent(Cad_Leitor.this, Formulario_Leitores.class);
                it.putExtra("Leitor",leitorEscolhido);
                startActivity(it);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id){
                leitor = (Leitor) adapter.getItemAtPosition(position);
                return false;
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar este Leitor");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                banco = new LivrariaBD (Cad_Leitor.this);
                banco.deletarLeitor(leitor);
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
        banco = new LivrariaBD(Cad_Leitor.this);
        listview_Leitor = banco.getLista_Leitor();
        banco.close();

        if(listview_Leitor != null){
            adapter = new ArrayAdapter<Leitor>(Cad_Leitor.this, android.R.layout.simple_list_item_1,listview_Leitor);
            lista.setAdapter(adapter);
        }


    }

}
