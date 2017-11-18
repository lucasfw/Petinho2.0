package com.example.lucascarvalho.adopet;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.lucascarvalho.adopet.loginregister.activities.LoginActivity;

import java.util.ArrayList;

public class UserAnimalList extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridView gridView;
    ArrayList<Animal> list;
    AnimalListAdapter adapter = null;
    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    public final static String teste = "com.example.lucascarvalho.adopet.Id";


    private String getAnimal(int id) {
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM ANIMAIS WHERE ID =" + id);

        String name = "aaa";
        while (cursor.moveToNext()) {
            int idA = cursor.getInt(0);
            name = cursor.getString(1);
            String porte = cursor.getString(2);
            int idade = cursor.getInt(3);
            String dono = cursor.getString(4);
            String sexo = cursor.getString(5);
            byte[] image1 = cursor.getBlob(6);
            byte[] image2 = cursor.getBlob(7);
            byte[] image3 = cursor.getBlob(8);
            byte[] image4 = cursor.getBlob(9);
            String especie = cursor.getString(10);
            String localizacao = cursor.getString(11);

        }
        adapter.notifyDataSetChanged();
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_animal_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new AnimalListAdapter(this, R.layout.animal_items, list);
        gridView.setAdapter(adapter);

        SQLiteHelper sqLiteHelper;
        sqLiteHelper = new SQLiteHelper(this, "AnimaisDB.sqlite", null, 1);
        SQLiteDatabase db = sqLiteHelper.getReadableDatabase();

        //get todos dados do sqlite
        String filtroEspecie = getIntent().getStringExtra("iEspecie");
        String filtroPorte = getIntent().getStringExtra("iPorte");
        String filtroSexo = getIntent().getStringExtra("iSexo");

//        if(filtroEspecie.equals("")){
//            filtroEspecie = null;
//    }
//        if(filtroPorte.equals("")) {
//            filtroPorte = null;
//        }
//        if(filtroSexo.equals("")){
//            filtroSexo = null;
//        }

        // array of columns to fetch
        String[] columns = {
                "*"
        };
        // selection criteria
          String selection = "especie" + " =?" + " AND " + "porte" + " = ?" + " AND " + "sexo" + " = ?";

        // selection arguments
        String[] selectionArgs = {filtroEspecie, filtroPorte, filtroSexo};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query("ANIMAIS", //Table to query
                columns,                    //columns to return
                null,                  //columns for the WHERE clause
                null,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order


        //Cursor cursor = db.rawQuery("SELECT * FROM ANIMAIS WHERE TRIM(especie) = '"+filtroEspecie.trim()+"' and TRIM(porte) = '"+filtroPorte.trim()+"' and TRIM(sexo) = '"+filtroSexo.trim()+"'",null);


        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);

            String name = cursor.getString(1);
            String porte = cursor.getString(2);
            int idade = cursor.getInt(3);
            String dono = cursor.getString(4);
            String sexo = cursor.getString(5);
            byte[] image1 = cursor.getBlob(6);
            byte[] image2 = cursor.getBlob(7);
            byte[] image3 = cursor.getBlob(8);
            byte[] image4 = cursor.getBlob(9);
            String especie = cursor.getString(10);
            String localizacao = cursor.getString(11);

            list.add(new Animal(id, name, porte,idade, dono,sexo, image1, image2, image3, image4, especie, localizacao));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM ANIMAIS");
                ArrayList<Integer> arrID = new ArrayList<Integer>();
                while (c.moveToNext()) {
                    arrID.add(c.getInt(0));
                }
                ;

                Intent i = new Intent(UserAnimalList.this, AnimalView.class);

                i.putExtra("iNome", MainActivity.sqLiteHelper.selectData(arrID.get(position)).getNome());
                i.putExtra("iDescricao", MainActivity.sqLiteHelper.selectData(arrID.get(position)).getPorte());
                i.putExtra("idd", arrID.get(position));

                startActivity(i);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_animal_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_camera:
                Intent i = new Intent(UserAnimalList.this,UserAnimalList.class);
                startActivity(i);
                break;
            case R.id.nav_gallery:
                Intent h = new Intent(UserAnimalList.this,LoginActivity.class);
                startActivity(h);
                break;
            case R.id.nav_slideshow:
                Intent s = new Intent(UserAnimalList.this,Sobre.class);
                startActivity(s);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
