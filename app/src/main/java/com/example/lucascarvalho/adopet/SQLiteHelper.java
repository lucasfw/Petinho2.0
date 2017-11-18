package com.example.lucascarvalho.adopet;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Lucas Carvalho on 07/11/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String nome, String porte,int idade, String dono, String sexo, byte[] image1, byte[] image2, byte[] image3, byte[] image4, String especie, String localizacao){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO ANIMAIS VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, nome);
        statement.bindString(2, porte);
        statement.bindDouble(3, (double)idade);
        statement.bindString(4, dono);
        statement.bindString(5, sexo);
        statement.bindBlob(6, image1);
        statement.bindBlob(7, image2);
        statement.bindBlob(8, image3);
        statement.bindBlob(9, image4);
        statement.bindString(10, especie);
        statement.bindString(11, localizacao);
        statement.executeInsert();
    }

    public void updateData (String nome, String porte,int idade, String dono, String sexo, byte[] image1, byte[] image2, byte[] image3, byte[] image4,String especie, String localizacao,int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE ANIMAIS SET nome = ?, porte = ?, idade = ?, dono = ?, sexo = ?, image1 = ?, image2 = ?, image3 = ?, image4 = ?, especie = ?, localizacao = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nome);
        statement.bindString(2, porte);
        statement.bindDouble(3, idade);
        statement.bindString(4, dono);
        statement.bindString(5, sexo);
        statement.bindBlob(6, image1);
        statement.bindBlob(7, image2);
        statement.bindBlob(8, image3);
        statement.bindBlob(9, image4);
        statement.bindString(10, especie);
        statement.bindString(11, localizacao);
        statement.bindDouble(12,(double)id);

        statement.execute();
        database.close();
    }
    //ATUALIZAR CAMPOS
    public Animal selectData(int id){
        String sql = "SELECT * FROM ANIMAIS WHERE id =" + id;
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        int idAnimal = id;

        String nome = cursor.getString(cursor.getColumnIndex("nome"));
        String porte = cursor.getString(cursor.getColumnIndex("porte"));
        int idade = cursor.getInt(cursor.getColumnIndex("idade"));
        String dono = cursor.getString(cursor.getColumnIndex("dono"));
        String sexo = cursor.getString(cursor.getColumnIndex("sexo"));
        byte[] image1 = cursor.getBlob(cursor.getColumnIndex("image1"));
        byte[] image2 = cursor.getBlob(cursor.getColumnIndex("image2"));
        byte[] image3 = cursor.getBlob(cursor.getColumnIndex("image3"));
        byte[] image4 = cursor.getBlob(cursor.getColumnIndex("image4"));
        String especie = cursor.getString(cursor.getColumnIndex("especie"));
        String localizacao = cursor.getString(cursor.getColumnIndex("localizacao"));
        StringBuilder conversor1 = new StringBuilder();
        StringBuilder conversor2 = new StringBuilder();
        StringBuilder conversor3 = new StringBuilder();
        StringBuilder conversor4 = new StringBuilder();
        StringBuilder conversor5 = new StringBuilder();
        StringBuilder conversor6 = new StringBuilder();
        StringBuilder conversor7 = new StringBuilder();
        StringBuilder conversor8 = new StringBuilder();
        StringBuilder conversor9 = new StringBuilder();
        StringBuilder conversor10 = new StringBuilder();
        StringBuilder conversor11 = new StringBuilder();
        conversor1.append(nome);
        conversor2.append(porte);
        conversor3.append(idade);
        conversor4.append(dono);
        conversor5.append(image1);
        conversor6.append(image2);
        conversor7.append(image3);
        conversor8.append(image4);
        conversor9.append(sexo);
        conversor10.append(especie);
        conversor11.append(localizacao);
        Animal a = new Animal(idAnimal, conversor1.toString(), conversor2.toString(),idade,conversor4.toString(),conversor9.toString(), image1,image2,image3,image4, conversor10.toString(), conversor11.toString());
        return a;
    }

    public void deleteData(int id){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM ANIMAIS WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double) id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
