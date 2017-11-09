package com.example.lucascarvalho.adopet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText etdTelefone = (EditText) findViewById(R.id.edtTelefone);
        final EditText etdNome = (EditText) findViewById(R.id.edtNome);
        final EditText etdEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText etdSenha = (EditText) findViewById(R.id.edtSenha);

        final Button btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
    }
}
