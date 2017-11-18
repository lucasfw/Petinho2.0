package com.example.lucascarvalho.adopet;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtPorte, edtIdade,edtDono,edtSexo, edtEspecie, edtLocalizacao;
    Button btnImg1,btnImg2,btnImg3,btnImg4, btnAdicionar, btnListar;
    RadioGroup groupPorte, groupEspecie, groupSexo;
    ImageView imgv1,imgv2,imgv3,imgv4;
    String radioPorte;
    String radioEspecie;
    String radioSexo;
    final int REQUEST_CODE_GALLERY = 999;
    final int REQUEST_CODE_GALLERY2 = 998;
    final int REQUEST_CODE_GALLERY3 = 997;
    final int REQUEST_CODE_GALLERY4 = 996;
    final String dono = null;
    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        init();
        final String emailFromIntent = getIntent().getStringExtra("EMAIL");
        final String ong = getIntent().getStringExtra("EHONG");

        if(!ong.equals("sim")){
            Intent i = new Intent(MainActivity.this,UserAnimalList.class);
            startActivity(i);
        }
        //edtDono.setText(emailFromIntent);
        sqLiteHelper = new SQLiteHelper(this, "AnimaisDB.sqlite", null, 1);
        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS ANIMAIS (Id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, porte VARCHAR,idade INT,dono VARCHAR, sexo VARCHAR, image1 BLOB,image2 BLOB,image3 BLOB,image4 BLOB,especie VARCHAR,localizacao VARCHAR)");

        btnImg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnImg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY2
                );
            }
        });
        btnImg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY3
                );
            }
        });
        btnImg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY4
                );
            }
        });

        groupPorte.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rbutton = (RadioButton) groupPorte.findViewById(checkedId);
                String resposta = rbutton.getText().toString();
                radioPorte = resposta;
            }
        });

        groupEspecie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rbutton = (RadioButton) groupEspecie.findViewById(checkedId);
                String resposta = rbutton.getText().toString();
                radioEspecie = resposta;
            }
        });

        groupSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rbutton = (RadioButton) groupSexo.findViewById(checkedId);
                String resposta = rbutton.getText().toString();
                radioSexo = resposta;
            }
        });

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            radioPorte,
                            Integer.parseInt(edtIdade.getText().toString().trim()),
                            emailFromIntent.toString().trim(),
                            radioSexo,
                            imageViewToByte(imgv1),
                            imageViewToByte(imgv2),
                            imageViewToByte(imgv3),
                            imageViewToByte(imgv4),
                            radioEspecie,
                            edtLocalizacao.getText().toString().trim()
                    );
                    Toast.makeText(getApplicationContext(),"Adicionado com Sucesso", Toast.LENGTH_SHORT).show();
                    edtName.setText("");
                    //edtPorte.setText("");
                    edtIdade.setText("");
                    //edtDono.setText("");
                    //edtSexo.setText("");
                    imgv1.setImageResource(R.mipmap.ic_launcher);
                    imgv2.setImageResource(R.mipmap.ic_launcher);
                    imgv3.setImageResource(R.mipmap.ic_launcher);
                    imgv4.setImageResource(R.mipmap.ic_launcher);
                    //edtEspecie.setText("");
                    edtLocalizacao.setText("");
                }
                catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Não foi Adicionado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AdvancedSearch.class);
                startActivity(intent);
            }
        });
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(),"Você não tem permissão para acessar o local do arquivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if(requestCode == REQUEST_CODE_GALLERY2){
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY2);
            }
            else {
                Toast.makeText(getApplicationContext(),"Você não tem permissão para acessar o local do arquivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if(requestCode == REQUEST_CODE_GALLERY3){
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY3);
            }
            else {
                Toast.makeText(getApplicationContext(),"Você não tem permissão para acessar o local do arquivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if(requestCode == REQUEST_CODE_GALLERY4){
            if(grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY4);
            }
            else {
                Toast.makeText(getApplicationContext(),"Você não tem permissão para acessar o local do arquivo", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    imgv1.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == REQUEST_CODE_GALLERY2 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgv2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == REQUEST_CODE_GALLERY3 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgv3.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode == REQUEST_CODE_GALLERY4 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgv4.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtName = (EditText) findViewById(R.id.edtNome);
        //edtPorte = (EditText) findViewById(R.id.edtPorte);
        edtIdade = (EditText) findViewById(R.id.edtIdade);
        //edtDono = (EditText) findViewById(R.id.edtDono);
        //edtSexo = (EditText) findViewById(R.id.edtSexo);
        btnImg1 = (Button) findViewById(R.id.btnImg1);
        btnImg2 = (Button) findViewById(R.id.btnImg2);
        btnImg3 = (Button) findViewById(R.id.btnImg3);
        btnImg4 = (Button) findViewById(R.id.btnImg4);
        btnAdicionar = (Button) findViewById(R.id.btnAdicionar);
        btnListar = (Button) findViewById(R.id.btnListar);
        imgv1 = (ImageView) findViewById(R.id.img1);
        imgv2 = (ImageView) findViewById(R.id.img2);
        imgv3 = (ImageView) findViewById(R.id.img3);
        imgv4 = (ImageView) findViewById(R.id.img4);
       // edtEspecie = (EditText) findViewById(R.id.edtEspecie);
        edtLocalizacao = (EditText) findViewById(R.id.edtLocalizacao);
        groupEspecie = (RadioGroup) findViewById(R.id.radioGroupEspecie);
        groupPorte = (RadioGroup) findViewById(R.id.radioGroupPorte);
        groupSexo = (RadioGroup) findViewById(R.id.radioGroupSexo);
    }


}
