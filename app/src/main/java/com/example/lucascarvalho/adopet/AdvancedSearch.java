package com.example.lucascarvalho.adopet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdvancedSearch extends AppCompatActivity {


    Button btnProcurar;
    EditText edtEspecie, edtPorte, edtSexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_search);

        btnProcurar = (Button) findViewById(R.id.btnProcurar);
        edtEspecie = (EditText) findViewById(R.id.edtEspecie);
        edtPorte = (EditText) findViewById(R.id.edtPorte);
        edtSexo = (EditText) findViewById(R.id.edtSexo);

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdvancedSearch.this, UserAnimalList.class);
                i.putExtra("iEspecie", edtEspecie.getText().toString().trim());
                i.putExtra("iPorte", edtPorte.getText().toString().trim());
                i.putExtra("iSexo", edtSexo.getText().toString().trim());
                startActivity(i);
            }
        });
    }




}
