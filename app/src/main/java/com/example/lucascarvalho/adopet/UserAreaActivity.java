package com.example.lucascarvalho.adopet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etdEmail = (EditText) findViewById(R.id.edtEmail);
        final EditText edtTelefone = (EditText) findViewById(R.id.edtTelefone);
        final TextView bemvindo = (TextView) findViewById(R.id.tviewBemVindo);
    }
}
