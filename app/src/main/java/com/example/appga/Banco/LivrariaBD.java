package com.example.appga.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.appga.Model.Categoria_Leitor;
import com.example.appga.Model.Categoria_Livro;
import com.example.appga.Model.Leitor;
import com.example.appga.Model.Livro;

import java.util.ArrayList;

public class LivrariaBD extends SQLiteOpenHelper {

    private static final String DATABASE = "Banco";
    private static final int VERSION = 3;

    public LivrariaBD (Context context){
        super(context, DATABASE, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE Cat_Livros ( codigo INTEGER PRIMARY KEY , descricao TEXT NOT NULL, aluguel_Maximo INTEGER NOT NULL, multa NUMERIC NOT NULL);");
        db.execSQL("CREATE TABLE Cat_Leitores ( codigo INTEGER PRIMARY KEY , descricao TEXT NOT NULL, aluguel_Maximo INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE Leitor ( nome TEXT NOT NULL, endereco TEXT NOT NULL, celular TEXT NOT NULL, email TEXT NOT NULL, " +
                " cpf TEXT CHECK(length(cpf)==14), data_Nascimento TEXT NOT NULL, categoria INTEGER, PRIMARY KEY(\"cpf\") "+
                ", FOREIGN KEY(\"categoria\") REFERENCES \"Cat_Leitor\"(\"cod_Cat\"));");
        db.execSQL("CREATE TABLE Livro ( codigo INTEGER PRIMARY KEY AUTOINCREMENT, isbn INTEGER NOT NULL, titulo TEXT NOT NULL," +
                "categoria INTEGER, autor TEXT NOT NULL, palavras_chaves TEXT, data_publicacao TEXT NOT NULL, edicao INTEGER NOT NULL," +
                "editora TEXT NOT NULL, paginas INTEGER NOT NULL, FOREIGN KEY(\"categoria\") REFERENCES \"Cat_Livros\"(\"cod_cat\"))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Cat_Livros");
        db.execSQL("DROP TABLE IF EXISTS Cat_Leitores");
        db.execSQL("DROP TABLE IF EXISTS Leitor");
        db.execSQL("DROP TABLE IF EXISTS Livro");
    }

    //Salvar

    public void salvarCategoriaLivro(Categoria_Livro categoriaLivro){
        ContentValues values = new ContentValues();

        values.put("codigo",categoriaLivro.getCodigo());
        values.put("descricao",categoriaLivro.getDescricao());
        values.put("aluguel_Maximo",categoriaLivro.getAluguel_Maximo());
        values.put("multa",categoriaLivro.getMulta());

        getWritableDatabase().insert("Cat_Livros",null,values);

    }

    public void salvarCategoriaLeitor(Categoria_Leitor categoriaLeitor){
        ContentValues values = new ContentValues();

        values.put("codigo",categoriaLeitor.getCodigo());
        values.put("descricao",categoriaLeitor.getDescricao());
        values.put("aluguel_Maximo",categoriaLeitor.getAluguel_Maximo());

        getWritableDatabase().insert("Cat_Leitores",null,values);

    }

    public void salvarLeitor(Leitor leitor){
        ContentValues values = new ContentValues();

        values.put("nome",leitor.getNome());
        values.put("endereco",leitor.getEndereco());
        values.put("celular",leitor.getCelular());
        values.put("email",leitor.getEmail());
        values.put("cpf",leitor.getCpf());
        values.put("data_Nascimento",leitor.getDt_Nascimento());
        values.put("categoria",leitor.getCategoria());


        getWritableDatabase().insert("Leitor",null,values);

    }

    public void salvarLivro(Livro livro){
        ContentValues values = new ContentValues();

        values.put("isbn",livro.getIsbn());
        values.put("titulo",livro.getTitulo());
        values.put("categoria",livro.getCategoria());
        values.put("autor",livro.getAutor());
        values.put("palavras_chaves",livro.getKeyWords());
        values.put("data_publicacao",livro.getDtPub());
        values.put("edicao",livro.getEdicao());
        values.put("editora",livro.getEditora());
        values.put("paginas",livro.getPaginas());

        getWritableDatabase().insert("Livro",null,values);

    }

    //Alterar

    public void alterarCategoriaLivro(Categoria_Livro categoriaLivro){
        ContentValues values = new ContentValues();

        values.put("codigo",categoriaLivro.getCodigo());
        values.put("descricao",categoriaLivro.getDescricao());
        values.put("aluguel_Maximo",categoriaLivro.getAluguel_Maximo());
        values.put("multa",categoriaLivro.getMulta());

        String [] args = {categoriaLivro.getCodigo()+""};
        getWritableDatabase().update("Cat_Livros",values,"codigo=?",args);
    }

    public void alterarCategoriaLeitor(Categoria_Leitor categoriaLeitor){
        ContentValues values = new ContentValues();

        values.put("codigo",categoriaLeitor.getCodigo());
        values.put("descricao",categoriaLeitor.getDescricao());
        values.put("aluguel_Maximo",categoriaLeitor.getAluguel_Maximo());

        String [] args = {categoriaLeitor.getCodigo()+""};
        getWritableDatabase().update("Cat_Leitores",values,"codigo=?",args);
    }

    public void alterarLeitor(Leitor leitor){
        ContentValues values = new ContentValues();

        values.put("nome",leitor.getNome());
        values.put("endereco",leitor.getEndereco());
        values.put("celular",leitor.getCelular());
        values.put("email",leitor.getEmail());
        values.put("cpf",leitor.getCpf());
        values.put("data_Nascimento",leitor.getDt_Nascimento());
        values.put("categoria",leitor.getCategoria());

        String [] args = {leitor.getCpf()};
        getWritableDatabase().update("Leitor",values,"cpf=?",args);
    }

    public void alterarLivro(Livro livro){
        ContentValues values = new ContentValues();

        values.put("isbn",livro.getIsbn());
        values.put("titulo",livro.getTitulo());
        values.put("categoria",livro.getCategoria());
        values.put("autor",livro.getAutor());
        values.put("palavras_chaves",livro.getKeyWords());
        values.put("data_publicacao",livro.getDtPub());
        values.put("edicao",livro.getEdicao());
        values.put("editora",livro.getEditora());
        values.put("paginas",livro.getPaginas());

        String [] args = {livro.getCodigo()+""};
        getWritableDatabase().update("Livro",values,"codigo=?",args);
    }

    //Deletar

    public void deletarCategoriaLivro(Categoria_Livro categoriaLivro){

        String [] args = {categoriaLivro.getCodigo()+""};
        getWritableDatabase().delete("Cat_Livros","codigo=?",args);

    }

    public void deletarCategoriaLeitor(Categoria_Leitor categoriaLeitor){

        String [] args = {categoriaLeitor.getCodigo()+""};
        getWritableDatabase().delete("Cat_Leitores","codigo=?",args);

    }

    public void deletarLeitor(Leitor leitor){

        String [] args = {leitor.getCpf()};
        getWritableDatabase().delete("Leitor","cpf=?",args);

    }

    public void deletarLivro(Livro livro){

        String [] args = {livro.getCodigo()+""};
        getWritableDatabase().delete("Livro","codigo=?",args);

    }

    //Listar

    public ArrayList <Categoria_Livro> getLista_CatLivro(){
        String [] columns = {"codigo","descricao","aluguel_Maximo","multa"};
        Cursor cursor = getWritableDatabase().query("Cat_Livros",columns,null,null,null,null,null,null);
        ArrayList <Categoria_Livro> categorias = new ArrayList<Categoria_Livro>();

        while (cursor.moveToNext()){
            Categoria_Livro categoria = new Categoria_Livro();
            categoria.setCodigo(cursor.getInt(0));
            categoria.setDescricao(cursor.getString(1));
            categoria.setAluguel_Maximo(cursor.getInt(2));
            categoria.setMulta(cursor.getFloat(3));

            categorias.add(categoria);
        }
        return  categorias;
    }

    public ArrayList <Categoria_Leitor> getLista_CatLeitor(){
        String [] columns = {"codigo","descricao","aluguel_Maximo"};
        Cursor cursor = getWritableDatabase().query("Cat_Leitores",columns,null,null,null,null,null,null);
        ArrayList <Categoria_Leitor> categorias = new ArrayList<Categoria_Leitor>();

        while (cursor.moveToNext()){
            Categoria_Leitor categoria = new Categoria_Leitor();
            categoria.setCodigo(cursor.getInt(0));
            categoria.setDescricao(cursor.getString(1));
            categoria.setAluguel_Maximo(cursor.getInt(2));

            categorias.add(categoria);
        }
        return  categorias;
    }

    public ArrayList <Leitor> getLista_Leitor(){
        String [] columns = {"nome","endereco","celular","email","cpf","data_Nascimento","categoria"};
        Cursor cursor = getWritableDatabase().query("Leitor",columns,null,null,null,null,null,null);
        ArrayList <Leitor> leitores = new ArrayList<Leitor>();

        while (cursor.moveToNext()){
            Leitor leitor = new Leitor();
            leitor.setNome(cursor.getString(0));
            leitor.setEndereco(cursor.getString(1));
            leitor.setCelular(cursor.getString(2));
            leitor.setEmail(cursor.getString(3));
            leitor.setCpf(cursor.getString(4));
            leitor.setDt_Nascimento(cursor.getString(5));
            leitor.setCategoria(cursor.getInt(6));

            leitores.add(leitor);
        }
        return  leitores;
    }

    public ArrayList <Livro> getLista_Livro(){
        String [] columns = {"codigo","isbn","titulo","categoria","autor","palavras_chaves","data_publicacao","edicao","editora","paginas"};
        Cursor cursor = getWritableDatabase().query("Livro",columns,null,null,null,null,null,null);
        ArrayList <Livro> livros = new ArrayList<Livro>();

        while (cursor.moveToNext()){
            Livro livro = new Livro();
            livro.setCodigo(cursor.getInt(0));
            livro.setIsbn(cursor.getInt(1));
            livro.setTitulo(cursor.getString(2));
            livro.setCategoria(cursor.getInt(3));
            livro.setAutor(cursor.getString(4));
            livro.setKeyWords(cursor.getString(5));
            livro.setDtPub(cursor.getString(6));
            livro.setEdicao(cursor.getInt(7));
            livro.setEditora(cursor.getString(8));
            livro.setPaginas(cursor.getInt(9));

            livros.add(livro);
        }
        return  livros;
    }
}
