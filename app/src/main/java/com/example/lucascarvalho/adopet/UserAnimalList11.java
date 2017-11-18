//package com.example.lucascarvalho.adopet;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.Dialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.example.lucascarvalho.adopet.loginregister.model.User;
//
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
///**
// * Created by Lucas Carvalho on 07/11/2017.
// */
//
//public class UserAnimalLis11 extends AppCompatActivity {
//
//    GridView gridView;
//    ArrayList<Animal> list;
//    AnimalListAdapter adapter = null;
//    public final static String teste = "com.example.lucascarvalho.adopet.Id";
//
//
//    private String getAnimal(int id) {
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ANIMAIS WHERE ID =" + id);
//
//        String name = "aaa";
//        while (cursor.moveToNext()) {
//            int idA = cursor.getInt(0);
//            name = cursor.getString(1);
//            String porte = cursor.getString(2);
//            int idade = cursor.getInt(3);
//            String dono = cursor.getString(4);
//            String sexo = cursor.getString(5);
//            byte[] image1 = cursor.getBlob(6);
//            byte[] image2 = cursor.getBlob(7);
//            byte[] image3 = cursor.getBlob(8);
//            byte[] image4 = cursor.getBlob(9);
//            String especie = cursor.getString(10);
//            String localizacao = cursor.getString(11);
//
//        }
//        adapter.notifyDataSetChanged();
//        return name;
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.animais_list_activity);
//
//        gridView = (GridView) findViewById(R.id.gridView);
//        list = new ArrayList<>();
//        adapter = new AnimalListAdapter(this, R.layout.animal_items, list);
//        gridView.setAdapter(adapter);
//
//        SQLiteHelper sqLiteHelper;
//        sqLiteHelper = new SQLiteHelper(this, "AnimaisDB.sqlite", null, 1);
//        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
//
//        //get todos dados do sqlite
//        String filtroEspecie = getIntent().getStringExtra("iEspecie");
//        String filtroPorte = getIntent().getStringExtra("iPorte");
//        String filtroSexo = getIntent().getStringExtra("iSexo");
//
//        // array of columns to fetch
//        String[] columns = {
//                "*"
//        };
//        // selection criteria
//        String selection = "especie" + " = ?" + " AND " + "porte" + " = ?" + " AND " + "sexo" + " = ?";
//
//        // selection arguments
//        String[] selectionArgs = {filtroEspecie, filtroPorte, filtroSexo};
//
//        // query user table with conditions
//        /**
//         * Here query function is used to fetch records from user table this function works like we use sql query.
//         * SQL query equivalent to this query function is
//         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
//         */
//        Cursor cursor = db.query("ANIMAIS", //Table to query
//                columns,                    //columns to return
//                selection,                  //columns for the WHERE clause
//                selectionArgs,              //The values for the WHERE clause
//                null,                       //group the rows
//                null,                       //filter by row groups
//                null);                      //The sort order
//
//
//        //Cursor cursor = db.rawQuery("SELECT * FROM ANIMAIS WHERE TRIM(especie) = '"+filtroEspecie.trim()+"' and TRIM(porte) = '"+filtroPorte.trim()+"' and TRIM(sexo) = '"+filtroSexo.trim()+"'",null);
//
//        String sql = "SELECT * FROM ANIMAIS WHERE especie =" + "'+filtroEspecie.trim()+'" + " and porte ="+ "'+filtroPorte+'" +" and sexo="+ "'+filtroSexo+'";
//        //Cursor cursor = MainActivity.sqLiteHelper.getData(sql);
//        list.clear();
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(0);
//
//            String name = cursor.getString(1);
//            String porte = cursor.getString(2);
//            int idade = cursor.getInt(3);
//            String dono = cursor.getString(4);
//            String sexo = cursor.getString(5);
//            byte[] image1 = cursor.getBlob(6);
//            byte[] image2 = cursor.getBlob(7);
//            byte[] image3 = cursor.getBlob(8);
//            byte[] image4 = cursor.getBlob(9);
//            String especie = cursor.getString(10);
//            String localizacao = cursor.getString(11);
//
//            list.add(new Animal(id, name, porte,idade, dono,sexo, image1, image2, image3, image4, especie, localizacao));
//        }
//        adapter.notifyDataSetChanged();
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM ANIMAIS");
//                ArrayList<Integer> arrID = new ArrayList<Integer>();
//                while (c.moveToNext()) {
//                    arrID.add(c.getInt(0));
//                }
//                ;
//
//                Intent i = new Intent(UserAnimalList.this, AnimalView.class);
//
//                i.putExtra("iNome", MainActivity.sqLiteHelper.selectData(arrID.get(position)).getNome());
//                i.putExtra("iDescricao", MainActivity.sqLiteHelper.selectData(arrID.get(position)).getPorte());
//                i.putExtra("idd", arrID.get(position));
//
//                startActivity(i);
//            }
//        });
//    }
//
//    private void updateAnimalList(){
//        //get todos dados do sqlite
//        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ANIMAIS");
//        list.clear();
//        while (cursor.moveToNext()){
//            int id = cursor.getInt(0);
//            String name = cursor.getString(1);
//            String porte = cursor.getString(2);
//            int idade = cursor.getInt(3);
//            String dono = cursor.getString(4);
//            String sexo = cursor.getString(5);
//            byte[] image1 = cursor.getBlob(6);
//            byte[] image2 = cursor.getBlob(7);
//            byte[] image3 = cursor.getBlob(8);
//            byte[] image4 = cursor.getBlob(9);
//            String especie = cursor.getString(10);
//            String localizacao = cursor.getString(11);
//
//            list.add(new Animal(id, name, porte, idade, dono,sexo,image1,image2,image3,image4,especie,localizacao));
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//}
